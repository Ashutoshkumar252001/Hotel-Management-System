package com.hotel.services;

import com.hotel.exception.RoomNotFoundException;
import com.hotel.models.RoomModel;

import java.util.List;

public interface RoomServices {

    RoomModel getRoomById(Long id) throws RoomNotFoundException;

    List<RoomModel> findAllRooms();

    void saveRoom(RoomModel room);


    void deleteRoom(Long id);

    List<RoomModel> findByHotelId(Long hotelId);
}

