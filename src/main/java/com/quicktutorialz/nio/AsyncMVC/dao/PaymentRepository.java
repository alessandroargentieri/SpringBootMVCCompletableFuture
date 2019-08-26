package com.quicktutorialz.nio.AsyncMVC.dao;

import com.quicktutorialz.nio.AsyncMVC.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, String>{
    Payment save(Payment payment);
}
