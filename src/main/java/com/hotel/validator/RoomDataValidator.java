package com.hotel.validator;

import com.hotel.models.RoomModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoomDataValidator implements DataValidator{

    @Override
    public List<String> validate(Object data) {
        List<String> errors = new ArrayList<>();
        RoomModel room = (RoomModel) data;
        if(room.getPricePerNight()<0)
            errors.add("price cannot be in a negative ");
      /*  if(Objects.equals(room.getRoomNumber(), room.getRoomNumber()) || room.getRoomNumber()!= null)
              errors.add("room number not same or null");*/
        return errors;
    }
}
