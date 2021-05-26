package com.yash.ecom.orderService.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yash.ecom.orderService.DTO.Email;
import com.yash.ecom.orderService.DTO.PlaceOrderDTO;
import com.yash.ecom.orderService.DTO.User;
import com.yash.ecom.orderService.domain.Address;
import com.yash.ecom.orderService.domain.CartDetail;
import com.yash.ecom.orderService.domain.Invoice;
import com.yash.ecom.orderService.domain.Order;
import com.yash.ecom.orderService.domain.OrderHistory;
import com.yash.ecom.orderService.domain.OrderItem;
import com.yash.ecom.orderService.domain.Payment;
import com.yash.ecom.orderService.domain.Shipping;
import com.yash.ecom.orderService.domain.ShoppingCart;
import com.yash.ecom.orderService.domain.State;
import com.yash.ecom.orderService.exception.CartEmptyException;
import com.yash.ecom.orderService.exception.ShoppingCartNotFoundException;
import com.yash.ecom.orderService.proxy.UserProxy;
import com.yash.ecom.orderService.repository.AddressRepository;
import com.yash.ecom.orderService.repository.CartDetailRepository;
import com.yash.ecom.orderService.repository.InvoiceRepository;
import com.yash.ecom.orderService.repository.OrderHistoryRepository;
import com.yash.ecom.orderService.repository.OrderRepository;
import com.yash.ecom.orderService.repository.PaymentRepository;
import com.yash.ecom.orderService.repository.ShippingRepository;
import com.yash.ecom.orderService.repository.ShoppingCartRepository;
import com.yash.ecom.orderService.service.OrderService;
import com.yash.ecom.orderService.utils.Constants;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private KafkaTemplate<String, Email> kafkaTemplate;

	private static final String TOPIC = "email";

	@Autowired
	private UserProxy userProxy;

	@Autowired
	private ShoppingCartRepository repo;

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private OrderHistoryRepository orderHistoryRepo;

	@Autowired
	private ShippingRepository shippingRepo;

	@Autowired
	private CartDetailRepository cartDetailRepo;

	@Autowired
	private PaymentRepository paymentRepo;
	
	@Autowired
	private AddressRepository addressRepo;
	
	@Autowired
	private InvoiceRepository invoiceRepo;
	
	@Autowired
	private ShippingChargeServiceImpl shippingService;

	@Transactional
	@Override
	public Order placeOrder(PlaceOrderDTO placeOrderDTO) {
		Invoice invoice = new Invoice();
		invoice.setAddressId(placeOrderDTO.getAddressId());
		invoice.setCreditCardId(placeOrderDTO.getCreditCardId());
		User user = userProxy.getUserById(placeOrderDTO.getUserAccountId());
		invoice.setInvoiceTo(user.getFullName());
		ShoppingCart cart = repo.findById(placeOrderDTO.getShoppingCartId()).orElseThrow(
				() -> new ShoppingCartNotFoundException(Constants.SHOPPING_CART_NOT_FOUND_EXCEPTION_MESSAGE));
		List<CartDetail> detail = cart.getCartDetails();
		if (detail.size() == 0)
			throw new CartEmptyException(Constants.CART_EMPTY_EXCEPTION_MESSAGE);
		Set<OrderItem> orderItems = new HashSet<OrderItem>();
		OrderItem orderItem;
		double total = 0;
		String details="";
		for (CartDetail item : detail) {
			orderItem = new OrderItem();
			orderItem.setInventoryItemId(item.getInventoryId());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setImageUrl(item.getImageUrl());
			orderItem.setPrice(item.getPrice());
			orderItem.setTax(item.getTax());
			orderItem.setTitle(item.getTitle());
			total += Math.round(item.getPrice()* item.getQuantity() * 100.0) / 100.0;
			// tax
			total += Math.round(item.getPrice()* item.getQuantity() * (item.getTax() / 100.00) * 100.0) / 100.0;
			details += "->"+orderItem.getTitle()+" ("+orderItem.getQuantity()+") subtotal(inc. tax):"+total+"\n"; 
			orderItems.add(orderItem);
		}
		Order order = saveOrder(placeOrderDTO, orderItems, total);

		order = saveShipping(placeOrderDTO, order, invoice);
		
		saveOrderHistory(order);

		savePayment(placeOrderDTO, total, order);

		updateCart(placeOrderDTO);

		sendEmail(user, order, details);
		return order;
	}

	private void sendEmail(User user, Order order, String details) {
		Email email = new Email();
		email.setSendTo(user.getEmail());
		email.setSubject("ORDER PLACED");
		email.setText("Hello "+user.getFullName()+"\n Order details:\n"+details+"Total amount:" + order.getTotalAmount()+"\nThank you , please keep shopping with us.");
		kafkaTemplate.send(TOPIC, email);
	}

	private void updateCart(PlaceOrderDTO placeOrderDTO) {
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setCartId(placeOrderDTO.getShoppingCartId());
		cartDetailRepo.deleteAllByShoppingCart(shoppingCart);
	}

	private void savePayment(PlaceOrderDTO placeOrderDTO, double total, Order order) {
		Payment payment = new Payment();
		payment.setAmount(total);
		payment.setOrderId(order.getOrderId());
		payment.setState(State.COMPLETED);
		payment.setCreditCardId(placeOrderDTO.getCreditCardId());
		paymentRepo.save(payment);
	}

	private Order saveShipping(PlaceOrderDTO placeOrderDTO, Order order, Invoice invoice) {
		Shipping shipping = new Shipping();
		Address address = addressRepo.findById(placeOrderDTO.getAddressId()).get();
		double shippingCharges = 0;
		shippingCharges = shippingService.getShippingCharge(address.getState().trim().toLowerCase()).getCharge();
		shipping.setShippingCharge(shippingCharges);
		shipping.setShippingMethod("Air");
		shipping.setAddressId(placeOrderDTO.getAddressId());
		shipping.setOrderId(order.getOrderId());
		shipping.setState(State.STARTED);
		shipping = shippingRepo.save(shipping);
		order.setTotalAmount(order.getTotalAmount()+shippingCharges);
		invoice.setShippingId(shipping.getShipppingId());
		invoice.setOrder(order);
		invoiceRepo.save(invoice);
		return order;
	}

	private void saveOrderHistory(Order order) {
		OrderHistory orderHistory = new OrderHistory();
		orderHistory.setOrderId(order.getOrderId());
		orderHistory.setAmount(order.getTotalAmount());
		orderHistoryRepo.save(orderHistory);
	}

	private Order saveOrder(PlaceOrderDTO placeOrderDTO, Set<OrderItem> orderItems, double total) {
		Order order = new Order();
		order.setUserAccountId(placeOrderDTO.getUserAccountId());
		order.setTotalAmount(Math.round(total * 100.0) / 100.0);
		order.setOrderItems(orderItems);
		order = orderRepo.save(order);
		return order;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Order> getAllOrders(Long userAccountId) {
		return orderRepo.findAllByUserAccountIdOrderByOrderIdDesc(userAccountId);
	}

}
