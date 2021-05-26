package com.yash.ecom.orderService.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Constants {

	public static final String SHOPPING_CART_NOT_FOUND_EXCEPTION_MESSAGE="Shopping Cart not found.";
	public static final String ADD_TO_CART_FALLBACK_MESSAGE="Could not add product to cart. Please try again later.";
	public static final String ADD_TO_WISHLIST_FALLBACK_MESSAGE="Could not add product to WishList. Please try again later.";
	public static final String CART_EMPTY_EXCEPTION_MESSAGE="Cart is empty";
	public static final String ITEM_ALREADY_EXISTS_EXCEPTION_MESSAGE="Item already exists in wish list";
	
	public static final Map<String , Double> SHIPPING_CHARGE = 	initMap();

	private static Map<String, Double> initMap() {
		Map<String, Double> map = new HashMap<>();
        map.put("maharashtra",100.00);
        map.put("delhi", 200.00);
        map.put("west bengal",300.00);
        map.put("chennai",400.00);
        map.put("heydrabad", 300.00);
        map.put("madhya pradesh", 50.00);
		return Collections.unmodifiableMap(map);
	} 
}
