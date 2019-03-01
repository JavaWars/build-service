package com.lazarev.exception;

public class BuildServiceApplicationException extends RuntimeException {
    public BuildServiceApplicationException() {
    }

    public BuildServiceApplicationException(String message) {
        super(message);
    }
}
