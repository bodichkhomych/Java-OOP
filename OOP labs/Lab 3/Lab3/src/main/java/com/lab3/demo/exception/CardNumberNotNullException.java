package com.lab3.demo.exception;

public class CardNumberNotNullException extends RuntimeException {
    public CardNumberNotNullException() {
        super("Card number is required");
    }
}
