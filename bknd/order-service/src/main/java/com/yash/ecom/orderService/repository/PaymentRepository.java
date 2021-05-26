package com.yash.ecom.orderService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.ecom.orderService.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

	Payment findByOrderId(Long orderId);

}
