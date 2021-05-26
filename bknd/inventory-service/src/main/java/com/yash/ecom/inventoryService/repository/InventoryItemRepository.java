package com.yash.ecom.inventoryService.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.ecom.inventoryService.domain.InventoryItem;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
	
	Page<InventoryItem> findAll(Pageable pageable);

}
