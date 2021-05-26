package com.yash.ecom.orderService.DTO;

import java.sql.Date;
import java.util.List;

import com.yash.ecom.orderService.domain.Address;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class InvoiceDTO {
	
	private Long invoiceId;
	
	private String invoiceTo;
	
	private Address address;
	
	private double shippingCharges;
	
	private double total;
	
	private Date date;
	
	private List<InvoiceItemDTO> invoiceItem;
	

}
