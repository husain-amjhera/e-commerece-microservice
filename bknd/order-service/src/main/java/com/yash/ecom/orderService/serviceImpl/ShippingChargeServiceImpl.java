package com.yash.ecom.orderService.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.yash.ecom.orderService.soap.shippingCharge.GetShippingCharge;
import com.yash.ecom.orderService.soap.shippingCharge.GetShippingChargeResponse;
import com.yash.ecom.orderService.soap.shippingCharge.ShippingCharge;

@Service
public class ShippingChargeServiceImpl {
	
	@Autowired
	private Jaxb2Marshaller marshaller;
	
	private WebServiceTemplate template;
	
	public ShippingCharge getShippingCharge(GetShippingCharge request) {
		template =  new WebServiceTemplate(marshaller);
		GetShippingChargeResponse charge = (GetShippingChargeResponse) template.marshalSendAndReceive("http://shipping-charge-service:8083/soapWS", request);
		return charge.getShippingCharge();
	}
	
	public ShippingCharge getShippingCharge(String state) {
		GetShippingCharge request = new GetShippingCharge();
		request.setName(state);
		template =  new WebServiceTemplate(marshaller);
		GetShippingChargeResponse charge = (GetShippingChargeResponse) template.marshalSendAndReceive("http://shipping-charge-service:8083/soapWS", request);
		return charge.getShippingCharge();
	}
}
