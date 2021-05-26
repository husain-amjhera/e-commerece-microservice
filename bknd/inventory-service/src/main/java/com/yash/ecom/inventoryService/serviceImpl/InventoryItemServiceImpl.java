package com.yash.ecom.inventoryService.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.yash.ecom.inventoryService.domain.InventoryItem;
import com.yash.ecom.inventoryService.exception.ProductNotFoundException;
import com.yash.ecom.inventoryService.repository.InventoryItemRepository;
import com.yash.ecom.inventoryService.service.InventoryItemService;
import com.yash.ecom.inventoryService.util.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InventoryItemServiceImpl implements InventoryItemService {

	@Autowired
	private InventoryItemRepository repo;

	@Override
	public List<InventoryItem> getAllItems() {
		log.info("entered into getAllItems method");
		log.info("Exit from getAllItems method");
		return repo.findAll();
	}

	@Override
	public InventoryItem getItemById(Long id) {
		log.info("entered into getItemById method");
		log.info("Exit from getItemById method");
		return repo.findById(id)
				.orElseThrow(() -> new ProductNotFoundException(Constants.PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE + id));
	}

	@Override
	public Page<InventoryItem> getPageItems(Pageable pageable) {
		log.info("entered into getPageItems method");
		log.info("Exit from getPageItems method");
		return repo.findAll(pageable);
	}

}
