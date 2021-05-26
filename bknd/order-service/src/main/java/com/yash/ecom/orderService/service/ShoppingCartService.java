package com.yash.ecom.orderService.service;

import com.yash.ecom.orderService.DTO.CartDetailDTO;
import com.yash.ecom.orderService.domain.ShoppingCart;

public interface ShoppingCartService {

	Long addShoppingCart(Long userSessionId);

	CartDetailDTO addToCart(CartDetailDTO cartDetailDTO);

	ShoppingCart getCart(Long cartId);

	void removeFromCart(Long shoppingCartId, Long cartDetailId);

	void updateCart(CartDetailDTO cartDetailDTO);

}
