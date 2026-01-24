package com.hotel_itc.repo;

import com.hotel_itc.models.BookingModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepo extends JpaRepository<BookingModel,Long> {
}
