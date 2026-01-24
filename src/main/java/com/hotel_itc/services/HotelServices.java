package com.hotel_itc.services;

import com.hotel_itc.exception.HotelNotFoundException;
import com.hotel_itc.models.HotelModel;
import com.hotel_itc.models.RoomModel;

import java.util.List;
import java.util.Map;

public interface HotelServices {
    HotelModel getHotelById(Long id) throws HotelNotFoundException;

    List<HotelModel> findAllHotels();

    void saveHotel(HotelModel hotel);

    Map<HotelModel,List<RoomModel>> getHotelMap();


    void deleteHotel(Long id);
}
