package com.yash.ecom.orderService.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class InvoiceItemDTO {

	private String title;
	
	private double price;
	
	private double tax;
	
	private double taxAmount;
	
	private double subTotal;
	
	private int quantity;
}
