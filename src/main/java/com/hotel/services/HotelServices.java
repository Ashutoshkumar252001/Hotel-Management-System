package com.hotel.services;

import com.hotel.exception.HotelNotFoundException;
import com.hotel.models.HotelModel;
import com.hotel.models.RoomModel;

import java.util.List;
import java.util.Map;

public interface HotelServices {
    HotelModel getHotelById(Long id) throws HotelNotFoundException;

    List<HotelModel> findAllHotels();

    void saveHotel(HotelModel hotel);

    //Map<HotelModel,List<RoomModel>> getHotelMap();


    void deleteHotel(Long id);
}
