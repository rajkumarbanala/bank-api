package com.example.demo.exception;

/**
 * @author Rajkumar Banala 12-Feb-2021
 *
 */


public class AppBaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AppBaseException() {
		super();
	}

	public AppBaseException(String message) {
		super(message);
	}
}
