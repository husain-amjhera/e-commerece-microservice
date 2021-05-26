package com.yash.ecom.orderService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.ecom.orderService.domain.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
