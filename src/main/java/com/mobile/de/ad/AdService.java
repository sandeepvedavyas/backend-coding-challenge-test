package com.mobile.de.ad;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.mobile.de.customer.CustomerRepository;
import com.mobile.de.customer.MobileCustomer;
import com.mobile.de.exception.CategoryNotFound;
import com.mobile.de.exception.MobileAdNotFound;
import com.mobile.de.exception.MobileCustomerNotFound;


@Service
public class AdService {
	
	@Autowired
    private AdRepository adRepository;
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public Ad create(Ad adData,Long customerId) {
		
		Optional<MobileCustomer> customerOptional = checkIfCustomerExists(customerId);
		checkIfValidCategory(adData);
		
		MobileAd mobileAd = inbound(adData);
		MobileCustomer mobileCustomer = customerOptional.get();
		mobileAd.setCustomer(mobileCustomer);
		
		mobileAd = adRepository.save(mobileAd);
		return outbound(mobileAd);
	}

	public Ad update(Ad adData, Long customerId, Long id){
		
		checkIfCustomerExists(customerId);
		checkIfMobileAdExists(id);
		checkIfValidCategory(adData);
		
		adData.setId(id);
		MobileAd updateMobileAd = inbound(adData);
		updateMobileAd = adRepository.save(updateMobileAd);
		return outbound(updateMobileAd);
	}


	public void delete(Long customerId,Long id){

		checkIfCustomerExists(customerId);
		checkIfMobileAdExists(id);
		adRepository.deleteById(id);
	}

	public Ad get(Long customerId, Long id){

		checkIfCustomerExists(customerId);
		Optional<MobileAd> mobileAd = checkIfMobileAdExists(id);

		Ad ad = outbound(mobileAd.get());
		return ad;
	}

	

	public List<Ad> list(Long customerId) {
		
		Optional<MobileCustomer> customerOptional = checkIfCustomerExists(customerId);
		return customerOptional.get().getAds()
				.stream()
				.map(this::outbound)
				.collect(Collectors.toList());
	}
	
	private Optional<MobileCustomer> checkIfCustomerExists(Long customerId) {
		Optional<MobileCustomer> customerOptional = customerRepository.findById(customerId);
		if(!customerOptional.isPresent()) 
			throw new MobileCustomerNotFound(messageSource.getMessage("customer.not.found", new Object[] { customerId },
					LocaleContextHolder.getLocale()));
		return customerOptional;
	}
	
	private Optional<MobileAd> checkIfMobileAdExists(Long id) {
		Optional<MobileAd> mobileAd = adRepository.findById(id);
		if (!mobileAd.isPresent())
			throw new MobileAdNotFound(messageSource.getMessage("ad.not.found", new Object[] { id },
					LocaleContextHolder.getLocale()));
		return mobileAd;
	}
	
	private void checkIfValidCategory(Ad adData) {
		if(!Arrays.stream(Category.values())
				.anyMatch((t) -> t.name().equalsIgnoreCase(adData.getCategory())))
			throw new CategoryNotFound(messageSource.getMessage("category.not.found", new Object[] { adData.getCategory() },
					LocaleContextHolder.getLocale()));
	}


    private Ad outbound(MobileAd mobileAd) {
        Ad adData = new Ad();
        adData.setId(mobileAd.getId());
        adData.setCategory(mobileAd.getCategory());
        adData.setMake(mobileAd.getMake());
        adData.setModel(mobileAd.getModel());
        adData.setDescription(mobileAd.getDescription());
        adData.setPrice(mobileAd.getPrice());
        return adData;
    }

    private MobileAd inbound(Ad adData) {
        MobileAd mobileAd = new MobileAd();
        mobileAd.setId(adData.getId());
        mobileAd.setCategory(adData.getCategory());
        mobileAd.setMake(adData.getMake());
        mobileAd.setModel(adData.getModel());
        mobileAd.setDescription(adData.getDescription());
        mobileAd.setPrice(adData.getPrice());
        return mobileAd;
    }

}
