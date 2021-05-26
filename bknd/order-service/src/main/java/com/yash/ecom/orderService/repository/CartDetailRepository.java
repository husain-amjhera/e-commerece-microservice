package com.yash.ecom.orderService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.ecom.orderService.domain.CartDetail;
import com.yash.ecom.orderService.domain.ShoppingCart;

public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {

	List<CartDetail> findAllByShoppingCart(ShoppingCart shoppingCart);

	void deleteByCartDetailIdAndShoppingCart(Long cartDetailId, ShoppingCart shoppingCart);

	void deleteAllByShoppingCart(ShoppingCart shoppingCart);
}
