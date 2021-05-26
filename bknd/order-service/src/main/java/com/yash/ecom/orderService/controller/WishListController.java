
package com.yash.ecom.orderService.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.ecom.orderService.DTO.WishListDTO;
import com.yash.ecom.orderService.domain.WishList;
import com.yash.ecom.orderService.service.WishListService;

@RestController
@RequestMapping("/v1/wish-list/")
public class WishListController {

	@Autowired
	private WishListService wishListService;

	@PostMapping
	public WishListDTO addToWishList(@Valid @RequestBody WishListDTO wishListDTO) {
		return wishListService.addToList(wishListDTO);
	}

	@GetMapping("/{userAccountId}")
	public List<WishList> getWishList(@PathVariable Long userAccountId) {
		return wishListService.getWishList(userAccountId);
	}

	@DeleteMapping("/{userAccountId}/{inventoryId}")
	public ResponseEntity<Map<String, String>> removeFromCart(@PathVariable Long userAccountId,
			@PathVariable Long inventoryId) {
		wishListService.removeFromCart(userAccountId, inventoryId);
		return new ResponseEntity<Map<String, String>>(
				Collections.singletonMap("message", "WishList item deleted successfully"), HttpStatus.OK);
	}
}
