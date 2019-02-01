package com.crossover.techtrial.exceptions;

public class NotFound404Exception extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotFound404Exception() {
		super();
	}

	public NotFound404Exception(final String message, final Throwable cause) {
		super(message, cause);
	}

	public NotFound404Exception(final String message) {
		super(message);
	}

	public NotFound404Exception(final Throwable cause) {
		super(cause);
	}

}