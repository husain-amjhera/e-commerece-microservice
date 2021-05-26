package com.yash.ecom.userService.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yash.ecom.userService.DTO.Address;
import com.yash.ecom.userService.DTO.UserProfileDTO;
import com.yash.ecom.userService.domain.CreditCard;
import com.yash.ecom.userService.domain.UserAccount;
import com.yash.ecom.userService.exception.UserNotFoundException;
import com.yash.ecom.userService.proxy.AddressProxy;
import com.yash.ecom.userService.repository.UserAccountRepository;
import com.yash.ecom.userService.service.CreditCardService;
import com.yash.ecom.userService.service.UserProfileService;

@Service
public class UserProfileServiceImpl implements UserProfileService{

	@Autowired
	private CreditCardService creditCardService;

	@Autowired
	private AddressProxy proxy;

	@Autowired
	private UserAccountRepository repo;

	@Override
	@Transactional(readOnly = true)
	public UserProfileDTO getUserProfile(Long userAccountId) {
		List<CreditCard> cards = creditCardService.getCards(userAccountId);
		List<Address> addresses = proxy.getAddressByUserAccountId(userAccountId);
		UserAccount user = repo.findById(userAccountId).orElseThrow(() -> new UserNotFoundException("User not found"));
		UserProfileDTO profile = new UserProfileDTO();
		profile.setAddress(addresses);
		profile.setFullName(user.getFullName());
		profile.setCards(cards);
		profile.setEmail(user.getEmail());
		return profile;
	}
}
