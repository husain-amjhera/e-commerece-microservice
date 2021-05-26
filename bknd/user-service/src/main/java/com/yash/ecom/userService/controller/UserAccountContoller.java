package com.yash.ecom.userService.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.ecom.userService.DTO.LoginResponse;
import com.yash.ecom.userService.DTO.UserDTO;
import com.yash.ecom.userService.DTO.UserRegistrationDTO;
import com.yash.ecom.userService.domain.UserAccount;
import com.yash.ecom.userService.service.UserAccountService;

@RestController
@RequestMapping("/v1/user/")
public class UserAccountContoller {

	@Autowired
	private UserAccountService userService;

	@PostMapping("")
	public UserAccount saveUser(@Valid @RequestBody UserRegistrationDTO registerRequest) {
		return userService.saveUser(registerRequest);
	}

	@PostMapping("/login")
	public LoginResponse loginUser(@Valid @RequestBody UserDTO loginRequest) {
		return userService.login(loginRequest);

	}

	@GetMapping("/{userAccountId}")
	public UserAccount getUserById(@PathVariable Long userAccountId) {
		return userService.getUserById(userAccountId);

	}
}
