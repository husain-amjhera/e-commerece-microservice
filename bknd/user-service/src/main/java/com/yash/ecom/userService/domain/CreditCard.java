package com.yash.ecom.userService.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class CreditCard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ccId;
	
	private String ccNumber;
	
	private String holderName;
	
	private Date expireDate;
	
	private Long userAccountId;
	
	
}
