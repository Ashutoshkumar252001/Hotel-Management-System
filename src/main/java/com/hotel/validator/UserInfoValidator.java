package com.hotel.validator;

import com.hotel.models.UserModel;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserInfoValidator implements DataValidator{

    @Override
    public List<String> validate(Object data) {
        UserModel user = (UserModel) data;
        List<String> errors = new ArrayList<>();

        if(StringUtils.isEmpty(user.getUserName())){
            errors.add("Username can not be null or empty");
        }
        if(user.getUserName() != null && user.getUserName().length() != 5){
            errors.add("Username must be of 5 characters");
        }
        if(StringUtils.isEmpty(user.getPassWord())){
            errors.add("Password can not be null or empty");
        }
        if(user.getPassWord() != null && user.getPassWord().length() <=5){
            errors.add("Password must be greater than 5 characters");
        }
        if(StringUtils.isEmpty(user.getEmail())){
            errors.add("Email can not be null or empty");
        }
        return errors;
    }
}
