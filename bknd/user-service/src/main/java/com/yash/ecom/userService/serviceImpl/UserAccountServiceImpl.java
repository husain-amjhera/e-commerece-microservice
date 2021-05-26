package com.yash.ecom.userService.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.yash.ecom.userService.DTO.LoginResponse;
import com.yash.ecom.userService.DTO.UserDTO;
import com.yash.ecom.userService.DTO.UserRegistrationDTO;
import com.yash.ecom.userService.domain.UserAccount;
import com.yash.ecom.userService.domain.UserSession;
import com.yash.ecom.userService.exception.EmailAlreadyTakenException;
import com.yash.ecom.userService.exception.UserNotFoundException;
import com.yash.ecom.userService.repository.UserAccountRepository;
import com.yash.ecom.userService.repository.UserSessionRepository;
import com.yash.ecom.userService.service.UserAccountService;
import com.yash.ecom.userService.utils.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserAccountServiceImpl implements UserAccountService {

	@Autowired
	private UserAccountRepository repo;

	@Autowired
	private UserSessionRepository sessionRepo;

	@Autowired
	private RestTemplate restTemplate;

	// TODO Add hystrix command and fallback
	@Transactional
	@Override
	public LoginResponse login(UserDTO loginRequest) {
		log.info("Entered in login method");
		UserAccount userAccount = repo.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword())
				.orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND_EXCEPTION_MESSAGE));

		UserSession userSession = sessionRepo.findByUserAccountId(userAccount.getUserAccountId())
				.orElseGet(() -> sessionRepo.save(new UserSession(userAccount.getUserAccountId())));
		log.info("Created UserSession for User with Id: " + userAccount.getUserAccountId());

		ResponseEntity<Long> shoppingCartId = restTemplate
				.postForEntity("http://order-service:8084/v1/shopping-cart/" + userSession.getSessionId(), null, Long.class);

		LoginResponse response = new LoginResponse();
		response.setEmail(userAccount.getEmail());
		response.setUserId(userAccount.getUserAccountId());
		response.setUserSessionId(userSession.getSessionId());
		response.setShoppingCartId(shoppingCartId.getBody());
		log.info("Exit from login method");
		return response;
	}

	@Transactional
	@Override
	public UserAccount saveUser(UserRegistrationDTO registerRequest) {
		log.info("Entered in saveUser method");
		Optional<UserAccount> userAccountNew = repo.findByEmail(registerRequest.getEmail());
		if (userAccountNew.isPresent()) {
			throw new EmailAlreadyTakenException(
					Constants.EMAIL_ALREADY_TAKEN_EXCEPTION_MESSAGE);
		} else {
			UserAccount userAccount = new UserAccount();
			userAccount.setEmail(registerRequest.getEmail());
			userAccount.setFullName(registerRequest.getFullName());
			userAccount.setPassword(registerRequest.getPassword());
			log.info("Exit from saveUser method");
			return repo.save(userAccount);
		}
	}

	@Transactional(readOnly = true)
	@Override
	public UserAccount getUserById(Long userAccountId) {
		log.info("Entered in getUserById method");
		log.info("Exit from getUserById method");
		return repo.findById(userAccountId).orElseThrow(() -> new UserNotFoundException("No user found"));
	}

}
