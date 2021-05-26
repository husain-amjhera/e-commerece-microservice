package com.yash.ecom.orderService.exception;

public class ShoppingCartNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ShoppingCartNotFoundException(String message) {
        super(message);
    }

}
