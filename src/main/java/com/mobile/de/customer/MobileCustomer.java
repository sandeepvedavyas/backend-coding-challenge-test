package com.mobile.de.customer;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.mobile.de.ad.MobileAd;

@Entity
public class MobileCustomer {
    
	@Id
	@GeneratedValue
    private Long id;
	
    private String firstName;
    
    private String lastName;
    
    private String companyName;
    
    private String email;
	
    @OneToMany(mappedBy="mobileCustomer")
	private List<MobileAd> ads;
    
    public MobileCustomer() {
		
	}

	public MobileCustomer(Long id,String firstName, String lastName, String companyName,String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.companyName = companyName;
		this.email = email;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
	public List<MobileAd> getAds() {
		return ads;
	}

	public void setAds(List<MobileAd> ads) {
		this.ads = ads;
	}

	@Override
	public String toString() {
		return "MobileCustomer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", companyName="
				+ companyName + ", email=" + email + "]";
	}
     
}
