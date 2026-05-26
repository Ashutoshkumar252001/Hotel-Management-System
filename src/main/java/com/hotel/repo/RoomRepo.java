package com.hotel.repo;

import com.hotel.models.HotelModel;
import com.hotel.models.RoomModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepo extends JpaRepository<RoomModel,Long> {

    List<RoomModel> findByHotel_Id(Long Id);
}
