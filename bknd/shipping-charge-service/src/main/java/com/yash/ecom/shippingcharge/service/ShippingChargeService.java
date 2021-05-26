package com.yash.ecom.shippingcharge.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.ecom.shippingcharge.repository.ShippingChargeRepository;
import com.yash.ecom.shippingcharge.soapData.ShippingCharge;

@Service
public class ShippingChargeService {
	
	@Autowired
	private ShippingChargeRepository repo;

	    public ShippingCharge getShippingCharge(String state) {
	    	
	    	Optional<com.yash.ecom.shippingcharge.domain.ShippingCharge> sc = repo.findByState(state);
	    	ShippingCharge response;
	    	
	    	if(sc.isPresent()) {
	    		response = new ShippingCharge();
	    		BeanUtils.copyProperties(sc.get(), response);
		        return response;
	    	}
	    	else {
	    		response = new ShippingCharge();
	    		response.setState("default");
	    		response.setCharge(500.00);
	    		return response;
	    	}
	    }
}
