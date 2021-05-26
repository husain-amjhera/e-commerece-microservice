package com.yash.ecom.orderService.service;

import java.util.List;

import com.yash.ecom.orderService.DTO.WishListDTO;
import com.yash.ecom.orderService.domain.WishList;

public interface WishListService {

	WishListDTO addToList(WishListDTO wishListDTO);

	void removeFromCart(Long userAccountId, Long wishListItemId);

	List<WishList> getWishList(Long userAccountId);

}
