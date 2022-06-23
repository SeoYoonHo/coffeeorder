package com.kakaopay.coffeeorder.exception;

public class NotEnoughPointException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotEnoughPointException() {
		super();
	}
	
	public NotEnoughPointException(String message) {
		super(message);
	}
	
	public NotEnoughPointException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public NotEnoughPointException(Throwable cause) {
		super(cause);
	}
	
}
