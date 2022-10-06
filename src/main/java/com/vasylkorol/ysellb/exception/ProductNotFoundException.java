package com.vasylkorol.ysellb.exception;

public class ProductNotFoundException extends RuntimeException {
    private static final String MESSAGE = "product not found with id: ";
    public ProductNotFoundException(int id) {
        super(MESSAGE + id);
    }
    public ProductNotFoundException(String message) {
        super(message);
    }
}
