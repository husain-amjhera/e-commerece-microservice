package com.yash.ecom.orderService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.ecom.orderService.domain.WishList;

public interface WishListRepository extends JpaRepository<WishList, Long> {

	void deleteByInventoryIdAndUserAccountId(Long inventoryId, Long userAccountId);
	
	List<WishList> findAllByUserAccountId(Long userAccountId);
	
	Optional<WishList> findByInventoryIdAndUserAccountId(Long inventoryId, Long userAccountId);

}
