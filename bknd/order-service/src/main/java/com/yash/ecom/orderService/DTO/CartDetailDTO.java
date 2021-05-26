package com.yash.ecom.orderService.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class CartDetailDTO {
	
	private Long cartDetailId;

	private Long inventoryId;

	private String title;

	private double price;

	private String imageUrl;

	private int quantity;

	private Long shoppingCartId;

	private double tax;

	private String message;
}
