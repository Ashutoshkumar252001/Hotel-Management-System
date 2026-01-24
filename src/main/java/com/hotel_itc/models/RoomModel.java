package com.hotel_itc.models;

import com.hotel_itc.enums.RoomStatus;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "rooms")
public class RoomModel extends BaseModel {

    @ManyToOne
   private HotelModel hotelModel;
   private Integer roomNumber;
   private String type;
   private double pricePerNight;

    @Enumerated(EnumType.STRING)
   private RoomStatus status;

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public HotelModel getHotelModel() {
        return hotelModel;
    }

    public void setHotelModel(HotelModel hotelModel) {
        this.hotelModel = hotelModel;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }
}
