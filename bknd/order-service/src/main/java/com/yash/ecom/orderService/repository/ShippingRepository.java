package com.yash.ecom.orderService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.ecom.orderService.domain.Shipping;

public interface ShippingRepository extends JpaRepository<Shipping, Long> {
	Optional<Shipping> findByOrderId(Long orderId);
}
