package com.yash.ecom.orderService.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Setter
@Getter
@Entity
public class CartDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartDetailId;
	
	private Long inventoryId;

	private String title;

	private double price;

	private String imageUrl;

	@CreationTimestamp
	private Date timeStamp;

	private int quantity;
	
	private double tax;

	@JsonIgnore
	@ManyToOne
	private ShoppingCart shoppingCart;
}
