package com.yash.ecom.userService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.ecom.userService.DTO.UserProfileDTO;
import com.yash.ecom.userService.service.UserProfileService;

@RestController
@RequestMapping("/v1/proflie/")
public class UserProfileContoller {

	@Autowired
	private UserProfileService userProfileService;

	@GetMapping("/{userAccountId}")
	public UserProfileDTO getUserById(@PathVariable Long userAccountId) {
		return userProfileService.getUserProfile(userAccountId);
	}
}
