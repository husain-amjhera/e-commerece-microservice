package com.yash.ecom.orderService.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yash.ecom.orderService.DTO.InvoiceDTO;
import com.yash.ecom.orderService.domain.Invoice;
import com.yash.ecom.orderService.domain.Order;
import com.yash.ecom.orderService.mapper.InvoiceMapper;
import com.yash.ecom.orderService.repository.InvoiceRepository;
import com.yash.ecom.orderService.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService{
	@Autowired
	private InvoiceRepository invoiceRepo;
	
	@Autowired
	private InvoiceMapper mapper;

	@Override
	@Transactional(readOnly = true)
	public Invoice getInvoiceById(Long invoiceId) {
		return invoiceRepo.findById(invoiceId).get();
	}
	
	@Override
	@Transactional(readOnly = true)
	public InvoiceDTO getInvoiceByOrderId(Long orderId) {
		Order order = new Order();
		order.setOrderId(orderId);
		Invoice invoice = invoiceRepo.findByOrder(order);
		return mapper.mapToDTO(invoice);
	}
}
