package com.hotel.repo;

import com.hotel.models.BookingModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepo extends JpaRepository<BookingModel,Long> {
}
