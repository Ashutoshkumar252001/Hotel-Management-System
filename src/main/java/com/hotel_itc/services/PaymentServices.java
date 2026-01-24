package com.hotel_itc.services;

import com.hotel_itc.exception.PaymentNotDoneException;
import com.hotel_itc.models.PaymentModel;

import java.util.List;

public interface PaymentServices {
    PaymentModel getPaymentById(Long id) throws PaymentNotDoneException;
    List<PaymentModel> findAllPayment();
    void savePayment(PaymentModel payment);
    void deletePayment(Long id);
}
