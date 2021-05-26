package com.yash.ecom.orderService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.ecom.orderService.domain.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
	Optional<ShoppingCart> findByUserSessionId(Long userSessionId);
}
