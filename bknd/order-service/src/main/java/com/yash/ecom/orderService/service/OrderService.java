package com.yash.ecom.orderService.service;

import java.util.List;

import com.yash.ecom.orderService.DTO.PlaceOrderDTO;
import com.yash.ecom.orderService.domain.Order;

public interface OrderService {

	Order placeOrder(PlaceOrderDTO placeOrderDTO);

	List<Order> getAllOrders(Long userAccountId);

}
