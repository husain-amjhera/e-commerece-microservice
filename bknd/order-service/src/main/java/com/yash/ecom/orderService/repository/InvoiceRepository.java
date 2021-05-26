package com.yash.ecom.orderService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.ecom.orderService.domain.Invoice;
import com.yash.ecom.orderService.domain.Order;

public interface InvoiceRepository extends JpaRepository<Invoice, Long>{

	Invoice findByOrder(Order order);
}
