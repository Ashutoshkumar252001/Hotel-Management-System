package com.hotel_itc.models;

import com.hotel_itc.enums.PaymentStatus;
import jakarta.persistence.*;


@Entity
@Table(name = "payments")
public class PaymentModel extends BaseModel {


    @OneToOne
    private BookingModel booking;
    private Double amount;

    private String paymentMode;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    public BookingModel getBooking() {
        return booking;
    }

    public void setBooking(BookingModel booking) {
        this.booking = booking;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }


    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
