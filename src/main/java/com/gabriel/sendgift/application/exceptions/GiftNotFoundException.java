package com.gabriel.sendgift.application.exceptions;

public class GiftNotFoundException extends RuntimeException {
    public GiftNotFoundException(String message) {
        super(message);
    }
}
