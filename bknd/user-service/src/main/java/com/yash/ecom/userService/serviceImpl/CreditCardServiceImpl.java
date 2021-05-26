package com.yash.ecom.userService.serviceImpl;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yash.ecom.userService.domain.CreditCard;
import com.yash.ecom.userService.repository.CreditCardRepository;
import com.yash.ecom.userService.service.CreditCardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CreditCardServiceImpl implements CreditCardService {
	
	@Autowired
	private CreditCardRepository repo;
	
	@Transactional
	@Override
	public CreditCard saveCard(CreditCard creditCard) {
		log.info("Entered into saveCard method");
		log.info("Exit from saveCard method");
		return repo.save(creditCard);
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<CreditCard> getCards(Long userAccountId) {
		log.info("Entered into getCards method");
		log.info("Exit from getCards method");
		return repo.findByUserAccountId(userAccountId);
	}
	
	@Transactional
	@Override
	public void deleteCard(Long ccId) {
		repo.deleteById(ccId);
		
	}

}
