package com.yash.ecom.orderService.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yash.ecom.orderService.DTO.InvoiceDTO;
import com.yash.ecom.orderService.DTO.InvoiceItemDTO;
import com.yash.ecom.orderService.domain.Address;
import com.yash.ecom.orderService.domain.Invoice;
import com.yash.ecom.orderService.domain.OrderItem;
import com.yash.ecom.orderService.domain.Shipping;
import com.yash.ecom.orderService.repository.AddressRepository;
import com.yash.ecom.orderService.repository.ShippingRepository;

@Component
public class InvoiceMapper {
	@Autowired
	private ShippingRepository shippingRepo;
	
	@Autowired
	private AddressRepository addressRepo;
	
	public InvoiceDTO mapToDTO(Invoice invoice) {
		List<InvoiceItemDTO> invoiceItems = new ArrayList<>();
		Shipping shipping = shippingRepo.findById(invoice.getShippingId()).get();
		Address address = addressRepo.findById(invoice.getAddressId()).get();
		InvoiceDTO dto = new InvoiceDTO();
		dto.setInvoiceId(invoice.getInvoiceId());
		dto.setAddress(address);
		dto.setInvoiceTo(invoice.getInvoiceTo());
		dto.setDate(invoice.getOrder().getOrderDate());
		dto.setShippingCharges(Math.round(shipping.getShippingCharge() * 100.00)/100.0);
		for(OrderItem item : invoice.getOrder().getOrderItems()) {
			InvoiceItemDTO invoiceItemDto = new InvoiceItemDTO();
			invoiceItemDto.setTitle(item.getTitle());
			invoiceItemDto.setQuantity(item.getQuantity());
			invoiceItemDto.setTax(item.getTax());
			invoiceItemDto.setPrice(item.getPrice());
			invoiceItemDto.setTaxAmount(Math.round(item.getPrice()* item.getQuantity() * (item.getTax() / 100.00) * 100.0) / 100.0);
			invoiceItemDto.setSubTotal(invoiceItemDto.getTaxAmount()+(item.getPrice()* item.getQuantity()));
			invoiceItems.add(invoiceItemDto);
		}
		dto.setInvoiceItem(invoiceItems);
		dto.setTotal(invoice.getOrder().getTotalAmount());
		return dto;
	}

}
