package com.yash.ecom.orderService.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

	private Long userAccountId;
	
	private String fullName;

	private String email;

	private String password;

}
