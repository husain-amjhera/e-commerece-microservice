package com.yash.ecom.userService.DTO;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.yash.ecom.userService.domain.CreditCard;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserProfileDTO {
	
	@NotBlank
	@Email
	private String email;

	private String fullName;
	
	private List<CreditCard> cards;
	
	private List<Address> address;
}
