package com.yash.ecom.inventoryService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.ecom.inventoryService.domain.InventoryItem;
import com.yash.ecom.inventoryService.service.InventoryItemService;

@RestController
@RequestMapping("/v1/inventory/")
public class InventoryController {

	@Autowired
	private InventoryItemService service;
	
//	@GetMapping
//	public List<InventoryItem> getAllItems(){
//		return service.getAllItems();
//	}
	
	@GetMapping
	public Page<InventoryItem> getAllItems(Pageable pageable){
		return service.getPageItems(pageable);
	}
	
	@GetMapping("/{id}")
	public InventoryItem getItemById(@PathVariable Long id){
		return service.getItemById(id);
	}
}
