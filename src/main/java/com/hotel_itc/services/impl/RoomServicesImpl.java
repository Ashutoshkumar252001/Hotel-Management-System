package com.hotel_itc.services.impl;

import com.hotel_itc.exception.RoomNotFoundException;
import com.hotel_itc.models.RoomModel;
import com.hotel_itc.repo.RoomRepo;
import com.hotel_itc.services.RoomServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServicesImpl implements RoomServices {

    @Autowired
    private RoomRepo roomRepo;

    @Override
    public RoomModel getRoomById(Long id) throws RoomNotFoundException {
        Optional<RoomModel> opt = roomRepo.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        } else {
            throw new RoomNotFoundException(
                    "Room not found for this ID: " + id);
        }
    }

    @Override
    public List<RoomModel> findAllRooms() {
        return roomRepo.findAll();
    }

    @Override
    public void saveRoom(RoomModel room) {
        if(room.getId()==null){
            roomRepo.save(room);
        }
        else {
            Optional<RoomModel> opt = roomRepo.findById(room.getId());
            if(opt.isEmpty()){
                throw new RoomNotFoundException("room not found by this id:"+room.getId());
            }
            RoomModel r2 = opt.get();
            r2.setRoomNumber(room.getRoomNumber());
            r2.setStatus(room.getStatus());
            r2.setType(room.getType());
            r2.setPricePerNight(room.getPricePerNight());
            roomRepo.save(r2);
        }
    }



    @Override
    public void deleteRoom(Long id) {
        Optional<RoomModel>opt = roomRepo.findById(id);
        if(opt.isEmpty()){
            throw new RoomNotFoundException("room not found by this id for deletion:"+id);

        }
        roomRepo.deleteById(id);
    }
}
