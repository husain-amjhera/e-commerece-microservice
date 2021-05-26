package com.yash.ecom.userService.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.yash.ecom.userService.DTO.Address;

@FeignClient(name = "order-service")
public interface AddressProxy {
	@GetMapping("/v1/address/{userAccountId}")
	public List<Address> getAddressByUserAccountId(@PathVariable("userAccountId") Long userAccountId);

}
