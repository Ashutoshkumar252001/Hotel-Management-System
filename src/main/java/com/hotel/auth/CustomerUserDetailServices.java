package com.hotel.auth;

import com.hotel.models.UserModel;
import com.hotel.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerUserDetailServices implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<UserModel> userOpt = userRepository.findByUserName(userName);
        if(userOpt.isEmpty()){
            throw new UsernameNotFoundException("User not found with username: " + userName);
        }

        UserModel user = userOpt.get();
        String[] rolesArray = user.getRoles().split(",");

        return User.builder()
                .username(user.getUserName())
                .password(user.getPassWord())
                .roles(rolesArray)
                .build();

    }
}

