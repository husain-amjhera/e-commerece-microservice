package com.yash.ecom.orderService.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceOrderDTO {

	private Long shoppingCartId;
	private Long userAccountId;
	private Long addressId;
	private Long creditCardId;
}
