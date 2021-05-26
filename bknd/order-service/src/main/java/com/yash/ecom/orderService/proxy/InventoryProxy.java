package com.yash.ecom.orderService.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.yash.ecom.orderService.DTO.InventoryItem;

@FeignClient(name = "inventory-service")
public interface InventoryProxy {
	@GetMapping("/v1/inventory/{inventoryId}")
	public InventoryItem getInventoryItem(@PathVariable("inventoryId") Long inventoryId);

}
