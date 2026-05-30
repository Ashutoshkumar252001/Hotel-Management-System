package com.hotel.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hotel.enums.RoomStatus;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;


@Entity
@Table(name = "rooms")
public class RoomModel extends BaseModel {

   @ManyToOne
   @JsonIgnoreProperties("rooms")
   @JoinColumn(name = "hotel_id")
   private HotelModel hotel;
   private Integer roomNumber;
   private String type;
   private Double pricePerNight;

    @Enumerated(EnumType.STRING)
   private RoomStatus status;

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(Double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }


    public HotelModel getHotel() {
        return hotel;
    }

    public void setHotel(HotelModel hotel) {
        this.hotel = hotel;
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
