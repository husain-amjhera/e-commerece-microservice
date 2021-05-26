package com.yash.ecom.orderService.DTO;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class InventoryItem {

	private Long id;

	private String title;

	private double price;

	private String imageUrl;

	private Date timeStamp;
	
	private double tax;
}
