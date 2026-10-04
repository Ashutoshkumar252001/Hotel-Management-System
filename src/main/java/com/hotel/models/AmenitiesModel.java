package com.hotel.models;

import jakarta.persistence.*;

@Entity
@Table(name = "amenities")
public class AmenitiesModel extends BaseModel {

    @OneToOne
    @JoinColumn(name = "hotel_id")
    private HotelModel hotel;

    private boolean wifi;
    private boolean television;
    private boolean telephone;
    private boolean restaurant;
    private boolean swimmingPool;
    private boolean gym;
    private boolean airportShuttle;
    private boolean freeParking;
    private boolean carRental;
    private boolean cctvSecurity;
    private boolean privateBalcony;
    private boolean jacuzzi;
    private boolean oceanView;
    private boolean privateBedding;

    public HotelModel getHotel() {
        return hotel;
    }

    public void setHotel(HotelModel hotel) {
        this.hotel = hotel;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isTelevision() {
        return television;
    }

    public void setTelevision(boolean television) {
        this.television = television;
    }

    public boolean isTelephone() {
        return telephone;
    }

    public void setTelephone(boolean telephone) {
        this.telephone = telephone;
    }

    public boolean isRestaurant() {
        return restaurant;
    }

    public void setRestaurant(boolean restaurant) {
        this.restaurant = restaurant;
    }

    public boolean isSwimmingPool() {
        return swimmingPool;
    }

    public void setSwimmingPool(boolean swimmingPool) {
        this.swimmingPool = swimmingPool;
    }

    public boolean isGym() {
        return gym;
    }

    public void setGym(boolean gym) {
        this.gym = gym;
    }

    public boolean isAirportShuttle() {
        return airportShuttle;
    }

    public void setAirportShuttle(boolean airportShuttle) {
        this.airportShuttle = airportShuttle;
    }

    public boolean isFreeParking() {
        return freeParking;
    }

    public void setFreeParking(boolean freeParking) {
        this.freeParking = freeParking;
    }

    public boolean isCarRental() {
        return carRental;
    }

    public void setCarRental(boolean carRental) {
        this.carRental = carRental;
    }

    public boolean isCctvSecurity() {
        return cctvSecurity;
    }

    public void setCctvSecurity(boolean cctvSecurity) {
        this.cctvSecurity = cctvSecurity;
    }

    public boolean isPrivateBalcony() {
        return privateBalcony;
    }

    public void setPrivateBalcony(boolean privateBalcony) {
        this.privateBalcony = privateBalcony;
    }

    public boolean isJacuzzi() {
        return jacuzzi;
    }

    public void setJacuzzi(boolean jacuzzi) {
        this.jacuzzi = jacuzzi;
    }

    public boolean isOceanView() {
        return oceanView;
    }

    public void setOceanView(boolean oceanView) {
        this.oceanView = oceanView;
    }

    public boolean isPrivateBedding() {
        return privateBedding;
    }

    public void setPrivateBedding(boolean privateBedding) {
        this.privateBedding = privateBedding;
    }
}
