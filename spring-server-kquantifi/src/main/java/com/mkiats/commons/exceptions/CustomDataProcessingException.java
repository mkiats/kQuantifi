package com.mkiats.commons.exceptions;

public class CustomDataProcessingException extends RuntimeException{
    public CustomDataProcessingException() {
        super();
    }

    public CustomDataProcessingException(String message) {
        super(message);
    }

    public CustomDataProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomDataProcessingException(Throwable cause) {
        super(cause);
    }
}
