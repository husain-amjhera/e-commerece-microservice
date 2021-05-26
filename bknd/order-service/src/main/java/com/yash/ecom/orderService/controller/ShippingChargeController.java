package com.yash.ecom.orderService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.ecom.orderService.serviceImpl.ShippingChargeServiceImpl;
import com.yash.ecom.orderService.soap.shippingCharge.GetShippingCharge;
import com.yash.ecom.orderService.soap.shippingCharge.ShippingCharge;

@RestController
@RequestMapping("/v1/shipping-charge/")
public class ShippingChargeController {

	@Autowired
	private ShippingChargeServiceImpl service;

	@PostMapping
	public ShippingCharge getShippingCharge(@RequestBody GetShippingCharge request) {
		return service.getShippingCharge(request);
	}
}
