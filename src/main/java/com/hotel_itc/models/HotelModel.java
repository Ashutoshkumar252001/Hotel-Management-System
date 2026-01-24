package com.hotel_itc.models;

import com.hotel_itc.enums.HotelRating;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;


@Entity
@Table(name = "hotels")
public class HotelModel extends BaseModel {

   @OneToMany
   private List<RoomModel> rooms;
   private String name;
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
