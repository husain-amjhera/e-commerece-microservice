package com.yash.ecom.orderService.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.yash.ecom.orderService.DTO.User;

@FeignClient(name = "user-service")
public interface UserProxy {
	@GetMapping("/v1/user/{userAccountId}")
	public User getUserById(@PathVariable("userAccountId") Long userAccountId);

}
