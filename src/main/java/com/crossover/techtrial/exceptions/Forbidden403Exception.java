package com.crossover.techtrial.exceptions;

public class Forbidden403Exception extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public Forbidden403Exception() {
		super();
	}

	public Forbidden403Exception(final String message, final Throwable cause) {
		super(message, cause);
	}

	public Forbidden403Exception(final String message) {
		super(message);
	}

	public Forbidden403Exception(final Throwable cause) {
		super(cause);
	}

}