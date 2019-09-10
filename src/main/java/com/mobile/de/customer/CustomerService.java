package com.mobile.de.customer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.mobile.de.exception.MobileCustomerNotFound;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private MessageSource messageSource;

	public Customer create(Customer customerData) {

		MobileCustomer mobileCustomer = inbound(customerData);
		mobileCustomer = customerRepository.save(mobileCustomer);
		return outbound(mobileCustomer);
	}

	public Customer update(Customer customerData, Long id) throws Exception {

		Optional<MobileCustomer> mobileCustomer = customerRepository.findById(id);
		if (!mobileCustomer.isPresent())
			throw new MobileCustomerNotFound(messageSource.getMessage("customer.not.found", new Object[] { id },
					LocaleContextHolder.getLocale()));

		customerData.setId(id);
		MobileCustomer updateCustomer = inbound(customerData);
		updateCustomer = customerRepository.save(updateCustomer);
		return outbound(updateCustomer);
	}

	public void delete(Long id) throws Exception {

		Optional<MobileCustomer> mobileCustomer = customerRepository.findById(id);
		if (!mobileCustomer.isPresent())
			throw new MobileCustomerNotFound(messageSource.getMessage("customer.not.found", new Object[] { id },
					LocaleContextHolder.getLocale()));

		customerRepository.deleteById(id);
	}

	public Customer get(Long id) throws Exception {

		Optional<MobileCustomer> mobileCustomer = customerRepository.findById(id);
		if (!mobileCustomer.isPresent())

			throw new MobileCustomerNotFound(messageSource.getMessage("customer.not.found", new Object[] { id },
					LocaleContextHolder.getLocale()));

		Customer customer = outbound(mobileCustomer.get());
		return customer;
	}

	public List<Customer> list() {
		return customerRepository
				.findAll()
				.stream()
				.map(this::outbound)
				.collect(Collectors.toList());
	}

	private Customer outbound(MobileCustomer mobileCustomer) {

		Customer customerData = new Customer();
		customerData.setId(mobileCustomer.getId());
		customerData.setFirstName(mobileCustomer.getFirstName());
		customerData.setLastName(mobileCustomer.getLastName());
		customerData.setCompanyName(mobileCustomer.getCompanyName());
		customerData.setEmail(mobileCustomer.getEmail());
		return customerData;
	}

	private MobileCustomer inbound(Customer customer) {

		MobileCustomer mobileCustomer = new MobileCustomer();
		mobileCustomer.setId(customer.getId());
		mobileCustomer.setFirstName(customer.getFirstName());
		mobileCustomer.setLastName(customer.getLastName());
		mobileCustomer.setCompanyName(customer.getCompanyName());
		mobileCustomer.setEmail(customer.getEmail());
		return mobileCustomer;
	}

}
