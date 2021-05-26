package com.yash.ecom.orderService.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WishListDTO {

	private Long inventoryId;

	private String title;

	private double price;

	private String imageUrl;

	private Long userAccountId;

	private String message;
}
