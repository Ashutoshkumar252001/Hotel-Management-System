package com.hotel_itc.exception;

public class PaymentNotDoneException extends RuntimeException {
    public PaymentNotDoneException(String message) {
        super(message);
    }
}
