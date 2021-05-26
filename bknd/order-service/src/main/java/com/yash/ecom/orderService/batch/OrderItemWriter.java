package com.yash.ecom.orderService.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.yash.ecom.orderService.domain.Order;
import com.yash.ecom.orderService.repository.OrderRepository;

public class OrderItemWriter implements ItemWriter<Order> {
	@Autowired
	private OrderRepository orderRepo;

	@Override
	public void write(List<? extends Order> items) throws Exception {
		orderRepo.saveAll(items);

	}

}
