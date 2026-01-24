package com.hotel_itc.services;

import com.hotel_itc.exception.RoomNotFoundException;
import com.hotel_itc.models.RoomModel;

import java.util.List;

public interface RoomServices {

    RoomModel getRoomById(Long id) throws RoomNotFoundException;

    List<RoomModel> findAllRooms();

    void saveRoom(RoomModel room);


    void deleteRoom(Long id);
}

