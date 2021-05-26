package com.yash.ecom.orderService.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Setter
@Getter
@Entity
public class OrderHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderHistoryId;
	
	private double amount;
	
	private State state= State.STARTED;
	
	private String notes;
	
	@CreationTimestamp
	private Date timeStamp;
	
	private Long orderId;

}
