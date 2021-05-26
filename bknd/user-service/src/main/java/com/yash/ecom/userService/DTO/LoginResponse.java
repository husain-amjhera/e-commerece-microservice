package com.yash.ecom.userService.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponse {

	private Long userId;
	
	private Long userSessionId;
	
	private Long shoppingCartId;

	private String email;
	
}
