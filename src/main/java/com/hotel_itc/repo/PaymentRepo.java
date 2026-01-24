package com.hotel_itc.repo;

import com.hotel_itc.models.PaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<PaymentModel,Long> {
}
