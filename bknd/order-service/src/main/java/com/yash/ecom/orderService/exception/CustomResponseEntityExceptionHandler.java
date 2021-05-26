package com.yash.ecom.orderService.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler
	public final ResponseEntity<ExceptionResponse> handleItemAlreadyExistException(ItemAlreadyExistException ex) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.OK);
	}
	
	@ExceptionHandler
	public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(ShoppingCartNotFoundException ex) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public final ResponseEntity<ExceptionResponse> handleCartEmptyException(CartEmptyException ex) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> errors = new HashMap<String, String>();

		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	}

}
