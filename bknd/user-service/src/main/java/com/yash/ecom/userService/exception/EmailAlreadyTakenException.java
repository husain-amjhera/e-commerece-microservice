package com.yash.ecom.userService.exception;

public class EmailAlreadyTakenException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmailAlreadyTakenException(String message) {
        super(message);
    }

}
