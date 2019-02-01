package com.crossover.techtrial.exceptions;

public class Unauthorized401Exception extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public Unauthorized401Exception() {
		super();
	}

	public Unauthorized401Exception(final String message, final Throwable cause) {
		super(message, cause);
	}

	public Unauthorized401Exception(final String message) {
		super(message);
	}

	public Unauthorized401Exception(final Throwable cause) {
		super(cause);
	}

}
