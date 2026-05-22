package com.hotel.services.impl;

import com.hotel.enums.Role;
import com.hotel.exception.UserNotCreatedException;
import com.hotel.models.UserModel;
import com.hotel.repo.UserRepository;
import com.hotel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void registerUser(UserModel user) throws UserNotCreatedException {
        try {
            user.setPassWord(passwordEncoder.encode(user.getPassWord()));
            // Roles are already set by the controller from form selection
            if (user.getRoles() == null || user.getRoles().isBlank()) {
                user.setRoles(Role.USER.getRoleName());
            }
            userRepository.save(user);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new UserNotCreatedException("User not created in system");
        }
}

}
