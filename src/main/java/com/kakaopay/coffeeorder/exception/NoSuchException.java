package com.kakaopay.coffeeorder.exception;

public class NoSuchException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoSuchException() {
		super();
	}
	
	public NoSuchException(String message) {
		super(message);
	}
	
	public NoSuchException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public NoSuchException(Throwable cause) {
		super(cause);
	}
	
}
