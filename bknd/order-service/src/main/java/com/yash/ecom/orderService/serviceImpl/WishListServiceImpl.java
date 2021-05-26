package com.yash.ecom.orderService.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yash.ecom.orderService.DTO.InventoryItem;
import com.yash.ecom.orderService.DTO.WishListDTO;
import com.yash.ecom.orderService.domain.WishList;
import com.yash.ecom.orderService.exception.ItemAlreadyExistException;
import com.yash.ecom.orderService.mapper.WishListMapper;
import com.yash.ecom.orderService.proxy.InventoryProxy;
import com.yash.ecom.orderService.repository.WishListRepository;
import com.yash.ecom.orderService.service.WishListService;
import com.yash.ecom.orderService.utils.Constants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WishListServiceImpl implements WishListService {

	@Autowired
	private WishListRepository repo;

	@Autowired
	private WishListMapper mapper;

	@Autowired
	private InventoryProxy proxy;

	@HystrixCommand(fallbackMethod = "addNothingToWishList",ignoreExceptions = {ItemAlreadyExistException.class}, commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000") })
	@Transactional
	@Override
	public WishListDTO addToList(WishListDTO wishListDTO) {
		log.info("Entered into addToList method");
		Optional<WishList> wishListCheck = repo
				.findByInventoryIdAndUserAccountId(wishListDTO.getInventoryId(), wishListDTO.getUserAccountId());
		if(wishListCheck.isPresent()) {
			 throw new ItemAlreadyExistException(Constants.ITEM_ALREADY_EXISTS_EXCEPTION_MESSAGE);
		}
		else {				
		WishList wishList = new WishList();
		InventoryItem inventoryItem = proxy.getInventoryItem(wishListDTO.getInventoryId());
		log.info("Got Item from inventory-service :" + inventoryItem.toString());
		wishList = mapper.mapToDomain(wishList, inventoryItem);
		wishList.setUserAccountId(wishListDTO.getUserAccountId());
		repo.save(wishList);
		wishListDTO = mapper.mapToDTO(wishList, inventoryItem);
		log.info("Exit from addToList method");
		return wishListDTO;
	}
	}

	@Transactional
	@Override
	public void removeFromCart(Long userAccountId, Long inventoryId) {
		log.info("Entered into removeFromCart method");
		repo.deleteByInventoryIdAndUserAccountId(inventoryId, userAccountId);
		log.info("Exit from removeFromCart method");
	}

	@Transactional(readOnly = true)
	@Override
	public List<WishList> getWishList(Long userAccountId) {
		log.info("Entered into getCart method");
		log.info("Exit from getCart method");
		return repo.findAllByUserAccountId(userAccountId);
	}

	public WishListDTO addNothingToWishList(WishListDTO wishListDTO) {
		log.info("Entered into addNothingToWishList method");
		wishListDTO.setMessage(Constants.ADD_TO_WISHLIST_FALLBACK_MESSAGE);
		log.info("Exit from addNothingToWishList method");
		return wishListDTO;
	}
}
