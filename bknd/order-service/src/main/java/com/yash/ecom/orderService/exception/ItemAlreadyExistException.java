package com.yash.ecom.orderService.exception;

public class ItemAlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ItemAlreadyExistException(String message) {
        super(message);
    }

}
