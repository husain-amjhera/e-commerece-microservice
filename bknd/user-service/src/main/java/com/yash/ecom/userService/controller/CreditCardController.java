package com.yash.ecom.userService.controller;

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

import com.yash.ecom.userService.domain.CreditCard;
import com.yash.ecom.userService.service.CreditCardService;

@RestController
@RequestMapping("/v1/credit-card/")
public class CreditCardController {
	@Autowired
	private CreditCardService cardService;
	
	@PostMapping
	public CreditCard saveCard(@Valid @RequestBody CreditCard creditCard) {
		return cardService.saveCard(creditCard);
	}
	
	@GetMapping("/{userAccountId}")
	public List<CreditCard> getCards(@PathVariable Long userAccountId) {
		return cardService.getCards(userAccountId);
	}
	
	@DeleteMapping("/{ccId}")
	public ResponseEntity<Map<String, String>> deleteCard(@PathVariable Long ccId) {
		cardService.deleteCard(ccId);
		return new ResponseEntity<Map<String, String>>(Collections.singletonMap("message", "Card deleted successfully"),
				HttpStatus.OK);
	}
}
