package com.hotel_itc.models;

import com.hotel_itc.enums.BookingStatus;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "bookings")
public class BookingModel extends BaseModel {


    @OneToOne
    private RoomModel room;

    @ManyToOne
    private CustomerModel customer;

    @OneToOne(cascade = CascadeType.ALL)
    private PaymentModel payment;

    @OneToOne
    private HotelModel hotel;


    @DateTimeFormat(pattern="yyyy-MM-dd")
   private LocalDate checkInDate;

    public HotelModel getHotel() {
        return hotel;
    }

    public void setHotel(HotelModel hotel) {
        this.hotel = hotel;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
   private LocalDate checkOutDate;




    @Enumerated(EnumType.STRING)
   private BookingStatus status;



    public LocalDate getCheckInDate() {
        return checkInDate;
    }



    public CustomerModel getCustomer() {
        return customer;
    }

    public RoomModel getRoom() {
        return room;
    }

    public void setRoom(RoomModel room) {
        this.room = room;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }


    public PaymentModel getPayment() {
        return payment;
    }

    public void setPayment(PaymentModel payment) {
        this.payment = payment;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }
}
