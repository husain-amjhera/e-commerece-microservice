package com.yash.ecom.orderService.service;

import com.yash.ecom.orderService.DTO.InvoiceDTO;
import com.yash.ecom.orderService.domain.Invoice;

public interface InvoiceService {

	Invoice getInvoiceById(Long invoiceId);

	InvoiceDTO getInvoiceByOrderId(Long orderId);

}
