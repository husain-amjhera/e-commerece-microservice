package com.yash.ecom.userService.service;

import com.yash.ecom.userService.DTO.LoginResponse;
import com.yash.ecom.userService.DTO.UserDTO;
import com.yash.ecom.userService.DTO.UserRegistrationDTO;
import com.yash.ecom.userService.domain.UserAccount;

public interface UserAccountService {

	LoginResponse login(UserDTO loginRequest);

	UserAccount getUserById(Long userAccountId);

	UserAccount saveUser(UserRegistrationDTO registerRequest);

}
