package com.mobile.de.ad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRepository extends JpaRepository<MobileAd, Long> {

	MobileAd findByModel(String model); // Note : an example method to demonstrate how to extend the JPA find by fields
}
