package com.hotel.services.impl;


import com.hotel.exception.HotelNotFoundException;
import com.hotel.models.HotelModel;
import com.hotel.models.RoomModel;
import com.hotel.repo.HotelRepo;
import com.hotel.repo.RoomRepo;
import com.hotel.services.HotelServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class HotelServicesImpl implements HotelServices {

    @Autowired
    private HotelRepo hotelRepo;

    @Autowired
    private RoomRepo roomRepo;

    @Override
    public HotelModel getHotelById(Long id) throws HotelNotFoundException {
        Optional<HotelModel> opt = hotelRepo.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        } else {
            throw new HotelNotFoundException(
                    "Hotel not found for this ID: " + id);
        }
    }




    @Override
    public List<HotelModel> findAllHotels() {
        return hotelRepo.findAll();
    }

    @Override
    public void saveHotel(HotelModel hotel) {
        if (hotel.getId() == null) {
            hotelRepo.save(hotel);

        } else {
            Optional<HotelModel> opt = hotelRepo.findById(hotel.getId());
            if (opt.isEmpty()) {
                throw new HotelNotFoundException("Hotel not fount with ID:" + hotel.getId());
            }
            HotelModel h = opt.get();
            h.setName(hotel.getName());
            h.setAddress(hotel.getAddress());
            h.setRating(hotel.getRating());
            hotelRepo.save(h);
        }
    }

    @Override
    public List<HotelModel> findAvailableHotelsByAddress(String address, LocalDate checkInDate, LocalDate checkOutDate) {
//        System.out.println("Address: " + address);
//        System.out.println("From: " + checkInDate);
//        System.out.println("To: " + checkOutDate);
//        return hotelRepo.findAvailableHotelsByAddress(address,checkInDate, checkOutDate);
        List<HotelModel> hotels =
                hotelRepo.findAvailableHotelsByAddress(address,checkInDate,checkOutDate);

        return hotels;

    }


    @Override
    public void deleteHotel(Long id) {

        Optional<HotelModel> opt= hotelRepo.findById(id);
        if(opt.isEmpty()){
            throw new HotelNotFoundException("Hotl not found by this for deletion ID:"+id);
        }
        hotelRepo.deleteById(id);

    }
}

