package com.yash.ecom.orderService.batch;

import org.springframework.batch.item.ItemProcessor;

import com.yash.ecom.orderService.domain.Order;
import com.yash.ecom.orderService.domain.State;

public class OrderItemProcessor implements ItemProcessor<Order, Order> {

	@Override
	public Order process(Order item) throws Exception {
		item.setState(State.COMPLETED);
		return item;
	}

}
