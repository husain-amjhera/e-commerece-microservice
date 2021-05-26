package com.yash.ecom.orderService.batch;


import org.springframework.batch.core.ItemProcessListener;

import com.yash.ecom.orderService.domain.Order;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderItemProcessorListner implements ItemProcessListener<Order,Order> {

	@Override
	public void beforeProcess(Order item) {
		log.info("before Process");
		
	}

	@Override
	public void afterProcess(Order item, Order result) {
		log.info("after Process :"+ item.toString()+"------>"+result.toString());
		
	}

	@Override
	public void onProcessError(Order item, Exception e) {
		log.info("Process error");
		
	}

}
