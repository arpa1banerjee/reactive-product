package com.example.product.exception;

public class ProductDBException extends RuntimeException{
    public ProductDBException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
    public ProductDBException(String msg) {
        super(msg);
    }
    public ProductDBException(Throwable throwable) {
        super(throwable);
    }
}
