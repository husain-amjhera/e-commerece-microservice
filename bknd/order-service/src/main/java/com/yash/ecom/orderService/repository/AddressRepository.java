package com.yash.ecom.orderService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.ecom.orderService.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
	List<Address> findAllByUserAccountId(Long userAccountId);
}
