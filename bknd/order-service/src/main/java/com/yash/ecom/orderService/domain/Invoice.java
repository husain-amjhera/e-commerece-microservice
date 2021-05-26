package com.yash.ecom.orderService.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long invoiceId;
	
	@CreationTimestamp
	private Date creationDate;
	
	private Long addressId;
	
	private Long creditCardId;
	
	private Long shippingId;
	
	private String invoiceTo;
	
	@OneToOne	
	private Order order;
	
}
