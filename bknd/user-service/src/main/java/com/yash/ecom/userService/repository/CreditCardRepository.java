package com.yash.ecom.userService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.ecom.userService.domain.CreditCard;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
	List<CreditCard> findByUserAccountId(Long userAccountId);
}
