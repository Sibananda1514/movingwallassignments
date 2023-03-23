package com.mindfire.exception;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 2617062245706792064L;

	public CustomException(String message) {
		super(message);
	}
}
