package com.hotel.models;

import com.hotel.enums.HotelRating;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "hotels",uniqueConstraints = {@UniqueConstraint(columnNames = {"hotel_name","address"})})
public class HotelModel extends BaseModel {

   @OneToMany
   private List<RoomModel> rooms;

    @Column(name = "hotel_name",nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    private HotelRating rating;

    public List<RoomModel> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomModel> rooms) {
        this.rooms = rooms;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public HotelRating getRating() {
        return rating;
    }

    public void setRating(HotelRating rating) {
        this.rating = rating;
    }
}
