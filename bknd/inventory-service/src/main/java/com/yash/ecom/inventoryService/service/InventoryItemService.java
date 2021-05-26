package com.yash.ecom.inventoryService.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yash.ecom.inventoryService.domain.InventoryItem;

public interface InventoryItemService {

	List<InventoryItem> getAllItems();

	InventoryItem getItemById(Long id);

	Page<InventoryItem> getPageItems(Pageable pageable);

}
