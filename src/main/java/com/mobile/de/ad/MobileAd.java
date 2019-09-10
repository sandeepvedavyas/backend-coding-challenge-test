package com.mobile.de.ad;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mobile.de.customer.MobileCustomer;

@Entity
public class MobileAd {
	
	@Id
	@GeneratedValue
    private Long id;

    private String make;

    private String model;

    private String description;

    private String category;

    private BigDecimal price;

    @ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private MobileCustomer mobileCustomer;
    
    public MobileAd() {
    	
	}

	public MobileAd(Long id, String make, String model, String description, String category, BigDecimal price) {
		super();
		this.id = id;
		this.make = make;
		this.model = model;
		this.description = description;
		this.category = category;
		this.price = price;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
	public MobileCustomer getCustomer() {
		return mobileCustomer;
	}

	public void setCustomer(MobileCustomer customer) {
		this.mobileCustomer = customer;
	}

	@Override
	public String toString() {
		return "MobileAd [id=" + id + ", make=" + make + ", model=" + model + ", description=" + description
				+ ", category=" + category + ", price=" + price + "]";
	}
    
    
}
