package com.mkiats.backtest.exceptions;

public class RatioDependencyCycleException extends RuntimeException {

	public RatioDependencyCycleException() {
		super();
	}

	public RatioDependencyCycleException(String message) {
		super(message);
	}

	public RatioDependencyCycleException(String message, Throwable cause) {
		super(message, cause);
	}

	public RatioDependencyCycleException(Throwable cause) {
		super(cause);
	}
}
