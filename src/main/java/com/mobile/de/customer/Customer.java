package com.mobile.de.customer;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(description="All details about the Mobile Customer")
public class Customer {
    
    private Long id;
    
    @NotNull
	@Size(min=2, message="First Name should have atleast 2 characters")
    @ApiModelProperty(notes="First Name should have atleast 2 characters")
    private String firstName;

    @NotNull
	@Size(min=2, message="Last Name should have atleast 2 characters")
	@ApiModelProperty(notes="Last Name should have atleast 2 characters, and can be combination of alpha-numeric characters")
    private String lastName;

	@NotNull
	@Size(min=2, message="Company Name should have atleast 2 characters")
	@ApiModelProperty(notes="Company Name should have atleast 2 characters")
    private String companyName;

    @Email
	@ApiModelProperty(notes="Should be of valid email format")
    private String email;
    
    public Customer() {

	}

    public Customer(Long id,
			@NotNull @Size(min = 2, message = "First Name should have atleast 2 characters") String firstName,
			@NotNull @Size(min = 2, message = "Last Name should have atleast 2 characters") String lastName,
			@NotNull @Size(min = 2, message = "Company Name should have atleast 2 characters") String companyName,
			@Email String email) {
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
}
