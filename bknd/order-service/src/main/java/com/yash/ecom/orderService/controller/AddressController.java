package com.yash.ecom.orderService.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.ecom.orderService.domain.Address;
import com.yash.ecom.orderService.service.AddressService;

@RestController
@RequestMapping("/v1/address/")
public class AddressController {
	@Autowired
	private AddressService addressService;

	@PostMapping
	public Address addAddress(@Valid @RequestBody Address address) {
		return addressService.addAddress(address);
	}

	@GetMapping("/{userAccountId}")
	public List<Address> getCards(@PathVariable Long userAccountId) {
		return addressService.getAddressByUserAccountId(userAccountId);
	}
	
	@DeleteMapping("/{addressId}")
	public ResponseEntity<Map<String, String>> deleteAddress(@PathVariable Long addressId) {
		addressService.deleteAddres(addressId);
		return new ResponseEntity<Map<String, String>>(Collections.singletonMap("message", "Address deleted successfully"),
				HttpStatus.OK);
	}
	

}
