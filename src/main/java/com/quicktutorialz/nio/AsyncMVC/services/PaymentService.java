package com.quicktutorialz.nio.AsyncMVC.services;

import com.quicktutorialz.nio.AsyncMVC.models.Payment;
import com.quicktutorialz.nio.AsyncMVC.models.PaymentDTO;

public interface PaymentService {
    Payment createPaymentFromDTO(PaymentDTO paymentDTO);
    Payment saveInDB(Payment payment);
}
