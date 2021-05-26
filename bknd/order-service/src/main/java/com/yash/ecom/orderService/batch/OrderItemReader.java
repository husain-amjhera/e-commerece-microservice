package com.yash.ecom.orderService.batch;

import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

import com.yash.ecom.orderService.domain.Order;
import com.yash.ecom.orderService.domain.State;
import com.yash.ecom.orderService.repository.OrderRepository;

public class OrderItemReader implements ItemReader<Order> {
	
	@Autowired
	private OrderRepository orderRepo;
	
	private List<Order> items;
	
	@BeforeStep
	public void before (StepExecution stepExecution) {
		items = orderRepo.findAllByState(State.PLACED);
	}

    public OrderItemReader() {
    }

	@Override
	public Order read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if (!items.isEmpty()) {
            return items.remove(0);
        }
		return null;
	}

}
