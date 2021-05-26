package com.yash.ecom.orderService.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yash.ecom.orderService.DTO.CartDetailDTO;
import com.yash.ecom.orderService.DTO.InventoryItem;
import com.yash.ecom.orderService.domain.CartDetail;
import com.yash.ecom.orderService.domain.ShoppingCart;
import com.yash.ecom.orderService.exception.ShoppingCartNotFoundException;
import com.yash.ecom.orderService.mapper.CartDetailMapper;
import com.yash.ecom.orderService.proxy.InventoryProxy;
import com.yash.ecom.orderService.repository.CartDetailRepository;
import com.yash.ecom.orderService.repository.ShoppingCartRepository;
import com.yash.ecom.orderService.service.ShoppingCartService;
import com.yash.ecom.orderService.utils.Constants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	private ShoppingCartRepository repo;

	@Autowired
	private CartDetailRepository cartDetailRepo;

	@Autowired
	private CartDetailMapper mapper;

	@Autowired
	private InventoryProxy proxy;

	@Transactional
	@Override
	public Long addShoppingCart(Long userSessionId) {
		log.info("Entered into addShoppingCart method");
		Optional<ShoppingCart> shoppingCartNew = repo.findByUserSessionId(userSessionId);
		if (shoppingCartNew.isPresent()) {
			log.info("Exit from addShoppingCart method");
			return shoppingCartNew.get().getCartId();
		} else {

			ShoppingCart shoppingCart = new ShoppingCart();
			shoppingCart.setActive(true);
			shoppingCart.setUserSessionId(userSessionId);
			Date now = new Date(System.currentTimeMillis());
			Date expiryDate = new Date(now.getTime() + (24 * 60 * 60 * 1000));
			shoppingCart.setExpireOn(new java.sql.Date(expiryDate.getTime()));
			shoppingCart = repo.save(shoppingCart);
			log.info("Exit from addShoppingCart method");
			return shoppingCart.getCartId();
		}
	}

	@HystrixCommand(fallbackMethod = "addNothingToCart", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000") })
	@Transactional
	@Override
	public CartDetailDTO addToCart(CartDetailDTO cartDetailDTO) {
		log.info("Entered into addToCart method");
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setCartId(cartDetailDTO.getShoppingCartId());
		List<CartDetail> cartDetails = cartDetailRepo.findAllByShoppingCart(shoppingCart);
		for(CartDetail cartDetail: cartDetails) {
			if(cartDetail.getInventoryId() == cartDetailDTO.getInventoryId()) {
				cartDetail.setQuantity(cartDetail.getQuantity()+cartDetailDTO.getQuantity());
				cartDetailRepo.save(cartDetail);
				return mapper.mapToDTO(cartDetail);
				
			}
		}
		InventoryItem inventoryItem = proxy.getInventoryItem(cartDetailDTO.getInventoryId());
		log.info("Got Item from inventory-service :" + inventoryItem.toString());
		CartDetail cartDetail = mapper.mapToDomain(cartDetailDTO, inventoryItem);
		cartDetailDTO = mapper.mapToDTO(cartDetailDTO, inventoryItem);
		cartDetailRepo.save(cartDetail);
		log.info("Exit from addToCart method");
		return cartDetailDTO;
	}

	@Transactional
	@Override
	public void removeFromCart(Long shoppingCartId, Long cartDetailId) {
		log.info("Entered into removeFromCart method");
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setCartId(shoppingCartId);
		cartDetailRepo.deleteByCartDetailIdAndShoppingCart(cartDetailId, shoppingCart);
		log.info("Exit from removeFromCart method");
	}

	@Transactional(readOnly = true)
	@Override
	public ShoppingCart getCart(Long cartId) {
		log.info("Entered into getCart method");
		log.info("Exit from getCart method");
		return repo.findById(cartId).orElseThrow(() -> new ShoppingCartNotFoundException(Constants.SHOPPING_CART_NOT_FOUND_EXCEPTION_MESSAGE));
	}
	
	@Transactional
	@Override
	public void updateCart(CartDetailDTO cartDetailDTO) {
		log.info("Entered into updateCart method");
		CartDetail cartDetail = mapper.mapToDomain(cartDetailDTO);
		log.info("updated cart item ->"+ cartDetail);
		cartDetailRepo.save(cartDetail);
		log.info("Exit from updateCart method");
	}
	

	public CartDetailDTO addNothingToCart(CartDetailDTO cartDetailDTO) {
		log.info("Entered into addNothingToCart method");
		cartDetailDTO.setMessage(Constants.ADD_TO_CART_FALLBACK_MESSAGE);
		log.info("Exit from addNothingToCart method");
		return cartDetailDTO;
	}
}
