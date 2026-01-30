package com.hotel.repo;

import com.hotel.models.PaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<PaymentModel,Long> {
}
