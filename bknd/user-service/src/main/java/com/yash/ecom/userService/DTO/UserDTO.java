package com.yash.ecom.userService.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
	
	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String password;
}
