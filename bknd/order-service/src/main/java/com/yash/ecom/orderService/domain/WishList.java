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
public class WishList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long wishListItemId;

	private Long inventoryId;

	private String title;

	private double price;

	private String imageUrl;

	@CreationTimestamp
	private Date timeStamp;

	private Long userAccountId;
}
