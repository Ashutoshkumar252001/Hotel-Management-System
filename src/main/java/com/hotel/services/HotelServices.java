package com.hotel.services;

import com.hotel.enums.HotelRating;
import com.hotel.exception.HotelNotFoundException;
import com.hotel.models.AmenitiesModel;
import com.hotel.models.HotelModel;
import com.hotel.models.RoomModel;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface HotelServices {
    HotelModel getHotelById(Long id) throws HotelNotFoundException;

    List<HotelModel> findAllHotels();
    List<AmenitiesModel> findAllAmenities();

    void saveHotel(HotelModel hotel);

    //Map<HotelModel,List<RoomModel>> getHotelMap();

    List<HotelModel> findAvailableHotelsByAddress(
            String address,
            LocalDate checkInDate,
            LocalDate checkOutDate);
    void deleteHotel(Long id);
    List<HotelModel> getSortedByRating(String field);
}
