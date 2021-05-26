package com.yash.ecom.orderService.batch;

import org.springframework.batch.core.ItemReadListener;

import com.yash.ecom.orderService.domain.Order;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderItemReaderListner implements ItemReadListener<Order> {

	@Override
	public void beforeRead() {
		log.info("before read");
		
	}

	@Override
	public void afterRead(Order item) {
		log.info("after read :"+ item.toString());
		
	}

	@Override
	public void onReadError(Exception ex) {
		log.info("read error");
		
	}

}
