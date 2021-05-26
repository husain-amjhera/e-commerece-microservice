
package com.yash.ecom.orderService.controller;

import java.util.Collections;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.ecom.orderService.DTO.CartDetailDTO;
import com.yash.ecom.orderService.domain.ShoppingCart;
import com.yash.ecom.orderService.service.ShoppingCartService;

@RestController
@RequestMapping("/v1/shopping-cart/")
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService shoppingCartService;

	@PostMapping("/{userSessionId}")
	public Long createCart(@PathVariable Long userSessionId) {
		return shoppingCartService.addShoppingCart(userSessionId);
	}

	@PostMapping("")
	public CartDetailDTO addToCart(@Valid @RequestBody CartDetailDTO cartDetailDTO) {
		return shoppingCartService.addToCart(cartDetailDTO);
	}

	@GetMapping("/{cartId}")
	public ShoppingCart getCart(@PathVariable Long cartId) {
		return shoppingCartService.getCart(cartId);
	}

	@DeleteMapping("/{shoppingCartId}/{cartDetailId}")
	public ResponseEntity<Map<String, String>> removeFromCart(@PathVariable Long shoppingCartId,
			@PathVariable Long cartDetailId) {
		shoppingCartService.removeFromCart(shoppingCartId, cartDetailId);
		return new ResponseEntity<Map<String, String>>(Collections.singletonMap("message", "Item deleted successfully"),
				HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Map<String, String>> updateCart(@Valid @RequestBody CartDetailDTO cartDetailDTO) {
		shoppingCartService.updateCart(cartDetailDTO);
		return new ResponseEntity<Map<String, String>>(Collections.singletonMap("message", "Item updated successfully"),
				HttpStatus.OK);
	}
}
