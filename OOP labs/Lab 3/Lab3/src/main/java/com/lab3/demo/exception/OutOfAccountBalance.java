package com.lab3.demo.exception;

public class OutOfAccountBalance extends RuntimeException {
    public OutOfAccountBalance(String message) {
        super(message);
    }
}
