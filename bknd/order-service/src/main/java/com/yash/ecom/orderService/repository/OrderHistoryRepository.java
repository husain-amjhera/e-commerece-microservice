package com.yash.ecom.orderService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.ecom.orderService.domain.OrderHistory;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {

	OrderHistory findByOrderId(Long orderId);
}
