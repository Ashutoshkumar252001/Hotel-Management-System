package com.hotel_itc.models;

import com.hotel_itc.enums.PaymentStatus;
import jakarta.persistence.*;

import java.awt.print.Book;

@Entity
@Table(name = "payments")
public class PaymentModel extends BaseModel {


    @OneToOne
    private BookingModel booking;
    private double amount;
    private String payment_mode;

    private PaymentStatus payment_status;

    public BookingModel getBooking() {
        return booking;
    }

    public void setBooking(BookingModel booking) {
        this.booking = booking;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPayment_mode() {
        return payment_mode;
    }

    public void setPayment_mode(String payment_mode) {
        this.payment_mode = payment_mode;
    }

    public PaymentStatus getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(PaymentStatus payment_status) {
        this.payment_status = payment_status;
    }
}
