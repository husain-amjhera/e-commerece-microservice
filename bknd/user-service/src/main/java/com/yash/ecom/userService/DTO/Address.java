package com.yash.ecom.userService.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class Address {

	private Long addressId;

	private String address1;

	private String address2;

	private String state;

	private String city;

	private String zip;

	private Long userAccountId;

}
