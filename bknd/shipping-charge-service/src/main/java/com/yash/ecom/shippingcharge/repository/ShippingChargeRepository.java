package com.yash.ecom.shippingcharge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.ecom.shippingcharge.domain.ShippingCharge;

public interface ShippingChargeRepository extends JpaRepository<ShippingCharge, Long> {
	
	Optional<ShippingCharge> findByState(String state);

}
