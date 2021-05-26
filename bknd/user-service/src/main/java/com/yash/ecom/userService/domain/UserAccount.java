package com.yash.ecom.userService.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class UserAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userAccountId;
	
	@Column(unique = true)
	private String email;
	
	private String fullName;
	
	private String password;
	
	@CreationTimestamp
	private Date timeStamp;
	
	private Long visits = 0l;
	
	private Long trans = 0l;
}
