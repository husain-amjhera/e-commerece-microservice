package com.yash.ecom.userService.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class UserSession {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sessionId;
	
	private String ipAddress;
	
	@CreationTimestamp
	private Date timeStamp;
	
	private Long userAccountId;

	public UserSession(Long userAccountId) {
		super();
		this.userAccountId = userAccountId;
	}
	
	
}
