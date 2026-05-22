package com.hotel.services;


import com.hotel.exception.UserNotCreatedException;
import com.hotel.models.UserModel;

public interface UserService {


    void registerUser(UserModel user) throws UserNotCreatedException;
}
