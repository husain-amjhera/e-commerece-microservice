package com.yash.ecom.orderService.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.ecom.orderService.DTO.PlaceOrderDTO;
import com.yash.ecom.orderService.domain.Order;
import com.yash.ecom.orderService.service.OrderService;
import com.yash.ecom.orderService.utils.Constants;

@RestController
@RequestMapping("/v1/order/")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@PostMapping("/place-order")
	public Order placeOrder(@RequestBody PlaceOrderDTO placeOrderDTO) {
		return orderService.placeOrder(placeOrderDTO);
	}
	
	@GetMapping("/{userAccountId}")
	public List<Order> placeOrder(@PathVariable Long userAccountId) {
		return orderService.getAllOrders(userAccountId);
	}
	
	@GetMapping("/shipping-charges")
	public Map<String,Double> getShippingCharges() {
		return Constants.SHIPPING_CHARGE;
	}

}
