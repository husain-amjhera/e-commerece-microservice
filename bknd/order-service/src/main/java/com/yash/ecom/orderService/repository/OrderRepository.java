package com.yash.ecom.orderService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.ecom.orderService.domain.Order;
import com.yash.ecom.orderService.domain.State;

public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findAllByUserAccountIdOrderByOrderIdDesc(Long userAccountId);
	List<Order> findAllByState(State state);
	
}
