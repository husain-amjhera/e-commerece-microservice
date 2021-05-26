package com.yash.ecom.orderService.batch;

import java.util.List;

import org.springframework.batch.core.ItemWriteListener;

import com.yash.ecom.orderService.domain.Order;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderItemWriterListner implements ItemWriteListener<Order> {

	@Override
	public void beforeWrite(List<? extends Order> items) {
		log.info("before Write");

	}

	@Override
	public void afterWrite(List<? extends Order> items) {
		for (Order item : items) {
			log.info("after Write :" + item.toString());
		}
	}

	@Override
	public void onWriteError(Exception exception, List<? extends Order> items) {
		log.info("Write error");

	}

}
