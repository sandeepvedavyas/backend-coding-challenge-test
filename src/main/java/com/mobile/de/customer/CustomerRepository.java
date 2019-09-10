package com.mobile.de.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<MobileCustomer, Long> {

	MobileCustomer findByCompanyName(String name);// Note : an example method to demonstrate how to extend the JPA find by fields
}
