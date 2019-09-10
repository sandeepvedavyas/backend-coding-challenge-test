package com.mobile.de.ad;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="All details about the Mobile Customer")
public class Ad {
    
    private Long id;

    @NotNull
   	@Size(min=2, message="Make should have atleast 2 characters")
    @ApiModelProperty(notes="Make should have atleast 2 characters")
    private String make;

    @NotNull
   	@Size(min=2, message="Model should have atleast 2 characters")
    @ApiModelProperty(notes="Model should have atleast 2 characters")
    private String model;

    private String description;

    @NotNull
   	@Size(min=2, message="First Name should have atleast 2 characters")
    @ApiModelProperty(notes="First Name should have atleast 2 characters")
    private String category;

    @Positive
    @ApiModelProperty(notes="Price should be a positive number")
    private BigDecimal price;

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
}
