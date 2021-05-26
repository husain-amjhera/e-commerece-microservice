package com.yash.ecom.orderService.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yash.ecom.orderService.domain.Address;
import com.yash.ecom.orderService.repository.AddressRepository;
import com.yash.ecom.orderService.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressRepository addressRepo;
	
	@Transactional
	@Override
	public Address addAddress(Address address) {
		return addressRepo.save(address);
		
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Address> getAddressByUserAccountId(Long userAccountId) {
		return addressRepo.findAllByUserAccountId(userAccountId);
		
	}
	
	
	@Transactional
	@Override
	public void deleteAddres(Long addressId) {
		addressRepo.deleteById(addressId);
		
	}
	

}
