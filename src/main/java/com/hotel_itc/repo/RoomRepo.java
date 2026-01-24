package com.hotel_itc.repo;

import com.hotel_itc.models.HotelModel;
import com.hotel_itc.models.RoomModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepo extends JpaRepository<RoomModel,Long> {

    List<RoomModel> findByHotelModel(HotelModel hotel);
}
