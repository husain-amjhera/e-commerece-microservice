package com.yash.ecom.orderService.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long addressId;

	@NotBlank
	private String address1;

	@NotBlank
	private String address2;

	@NotBlank
	private String state;

	@NotBlank
	private String city;

	@NotNull
	@Size(min = 6 , max = 6)
	private String zip;
	
	private Long userAccountId;

}
