package com.yash.ecom.shippingcharge.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.yash.ecom.shippingcharge.service.ShippingChargeService;
import com.yash.ecom.shippingcharge.soapData.GetShippingCharge;
import com.yash.ecom.shippingcharge.soapData.GetShippingChargeResponse;
import com.yash.ecom.shippingcharge.soapData.ShippingCharge;

@Endpoint
public class ShippingChargeEndpoint {

	@Autowired
	private ShippingChargeService service;
	
	@PayloadRoot(namespace = "http://yash.com/ecom/shippingCharge", localPart = "getShippingCharge")
	@ResponsePayload
	public GetShippingChargeResponse getShippingChargeRequest(@RequestPayload GetShippingCharge request){
		System.out.println("<---------------ENDPOINT CALLED ------->");
		ShippingCharge charges = service.getShippingCharge(request.getName());
		GetShippingChargeResponse response = new GetShippingChargeResponse();
		response.setShippingCharge(charges);
		return response;
	}
}
