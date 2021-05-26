package com.yash.ecom.userService.service;

import java.util.List;

import com.yash.ecom.userService.domain.CreditCard;

public interface CreditCardService {

	CreditCard saveCard(CreditCard creditCard);

	List<CreditCard> getCards(Long userAccountId);

	void deleteCard(Long ccId);

}
