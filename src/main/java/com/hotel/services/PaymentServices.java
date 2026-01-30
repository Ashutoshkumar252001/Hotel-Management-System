package com.hotel.services;

import com.hotel.exception.PaymentNotDoneException;
import com.hotel.models.PaymentModel;

import java.util.List;

public interface PaymentServices {
    PaymentModel getPaymentById(Long id) throws PaymentNotDoneException;
    List<PaymentModel> findAllPayment();
    void savePayment(PaymentModel payment);
    void deletePayment(Long id);
}
