package com.yash.ecom.orderService.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Shipping {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long shipppingId;

	private String shippingMethod;

	private double shippingCharge;

	private State state = State.STARTED;

	private Long orderId;
	
	private Long addressId;
}
