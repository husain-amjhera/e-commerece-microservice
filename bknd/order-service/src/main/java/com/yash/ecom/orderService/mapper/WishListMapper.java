package com.yash.ecom.orderService.mapper;

import org.springframework.stereotype.Component;

import com.yash.ecom.orderService.DTO.InventoryItem;
import com.yash.ecom.orderService.DTO.WishListDTO;
import com.yash.ecom.orderService.domain.WishList;

@Component
public class WishListMapper {

	public WishListDTO mapToDTO(WishList wishList, InventoryItem inventoryItem) {
		WishListDTO wishListDto = new WishListDTO();
		wishListDto.setImageUrl(inventoryItem.getImageUrl());
		wishListDto.setInventoryId(inventoryItem.getId());
		wishListDto.setPrice(inventoryItem.getPrice());
		wishListDto.setTitle(inventoryItem.getTitle());
		wishListDto.setUserAccountId(wishList.getUserAccountId());
		return wishListDto;

	}
	
	public WishList mapToDomain(WishList wishList, InventoryItem inventoryItem) {
		wishList.setImageUrl(inventoryItem.getImageUrl());
		wishList.setInventoryId(inventoryItem.getId());
		wishList.setPrice(inventoryItem.getPrice());
		wishList.setTitle(inventoryItem.getTitle());
		return wishList;

	}
}
