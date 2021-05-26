package com.yash.ecom.orderService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.ecom.orderService.DTO.InvoiceDTO;
import com.yash.ecom.orderService.service.InvoiceService;

@RestController
@RequestMapping("/v1/invoice/")
public class InvoiceController {
	@Autowired
	private InvoiceService invoiceService;

	@GetMapping("/{orderId}")
	public InvoiceDTO getInvoice(@PathVariable Long orderId) {
		return invoiceService.getInvoiceByOrderId(orderId);
	}

}
