package com.yash.ecom.orderService.service;

import java.util.List;

import com.yash.ecom.orderService.domain.Address;

public interface AddressService {

	Address addAddress(Address address);

	List<Address> getAddressByUserAccountId(Long userAccountId);

	void deleteAddres(Long addressId);

}
