package com.yash.ecom.orderService.mapper;

import org.springframework.stereotype.Component;

import com.yash.ecom.orderService.DTO.CartDetailDTO;
import com.yash.ecom.orderService.DTO.InventoryItem;
import com.yash.ecom.orderService.domain.CartDetail;
import com.yash.ecom.orderService.domain.ShoppingCart;

@Component
public class CartDetailMapper {

	public CartDetail mapToDomain(CartDetailDTO cartDetailDTO, InventoryItem inventoryItem) {
		CartDetail cartDetail = new CartDetail();
		cartDetail.setImageUrl(inventoryItem.getImageUrl());
		cartDetail.setInventoryId(cartDetailDTO.getInventoryId());
		cartDetail.setPrice(inventoryItem.getPrice());
		cartDetail.setQuantity(cartDetailDTO.getQuantity());
		cartDetail.setTitle(inventoryItem.getTitle());
		cartDetail.setTax(inventoryItem.getTax());
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setCartId(cartDetailDTO.getShoppingCartId());
		cartDetail.setShoppingCart(shoppingCart);
		return cartDetail;

	}

	public CartDetailDTO mapToDTO(CartDetailDTO cartDetailDTO, InventoryItem inventoryItem) {
		cartDetailDTO.setImageUrl(inventoryItem.getImageUrl());
		cartDetailDTO.setPrice(inventoryItem.getPrice());
		cartDetailDTO.setTitle(inventoryItem.getTitle());
		cartDetailDTO.setTax(inventoryItem.getTax());
		return cartDetailDTO;
	}

	public CartDetailDTO mapToDTO(CartDetail cartDetail) {
		CartDetailDTO cartDetailDTO = new CartDetailDTO();
		cartDetailDTO.setInventoryId(cartDetail.getInventoryId());
		cartDetailDTO.setQuantity(cartDetail.getQuantity());
		cartDetailDTO.setShoppingCartId(cartDetail.getShoppingCart().getCartId());
		cartDetailDTO.setImageUrl(cartDetail.getImageUrl());
		cartDetailDTO.setPrice(cartDetail.getPrice());
		cartDetailDTO.setTitle(cartDetail.getTitle());
		cartDetailDTO.setTax(cartDetail.getTax());
		return cartDetailDTO;
	}

	public CartDetail mapToDomain(CartDetailDTO cartDetailDTO) {
		CartDetail cartDetail = new CartDetail();
		cartDetail.setCartDetailId(cartDetailDTO.getCartDetailId());
		cartDetail.setImageUrl(cartDetailDTO.getImageUrl());
		cartDetail.setInventoryId(cartDetailDTO.getInventoryId());
		cartDetail.setPrice(cartDetailDTO.getPrice());
		cartDetail.setQuantity(cartDetailDTO.getQuantity());
		cartDetail.setTitle(cartDetailDTO.getTitle());
		cartDetail.setTax(cartDetailDTO.getTax());
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setCartId(cartDetailDTO.getShoppingCartId());
		cartDetail.setShoppingCart(shoppingCart);
		return cartDetail;

	}
}
