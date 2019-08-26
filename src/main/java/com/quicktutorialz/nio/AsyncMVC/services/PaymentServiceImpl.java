package com.quicktutorialz.nio.AsyncMVC.services;

import com.quicktutorialz.nio.AsyncMVC.dao.PaymentRepository;
import com.quicktutorialz.nio.AsyncMVC.models.Payment;
import com.quicktutorialz.nio.AsyncMVC.models.PaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment createPaymentFromDTO(PaymentDTO paymentDTO) {
        return new Payment(paymentDTO.getValue(), paymentDTO.getCommitter(), paymentDTO.getCommittee());
    }

    @Override
    public Payment saveInDB(Payment payment) {
        return paymentRepository.save(payment);
    }
}
