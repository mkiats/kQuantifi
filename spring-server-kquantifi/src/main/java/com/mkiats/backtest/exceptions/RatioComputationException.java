package com.mkiats.backtest.exceptions;

public class RatioComputationException extends RuntimeException {

	public RatioComputationException() {
		super();
	}

	public RatioComputationException(String message) {
		super(message);
	}

	public RatioComputationException(String message, Throwable cause) {
		super(message, cause);
	}

	public RatioComputationException(Throwable cause) {
		super(cause);
	}
}
