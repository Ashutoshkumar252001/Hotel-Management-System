package com.hotel_itc.validator;

import java.util.List;

public interface DataValidator<T>{
    List<String> validate(T data);
}
