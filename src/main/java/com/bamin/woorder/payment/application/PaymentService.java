package com.bamin.woorder.payment.application;

import com.bamin.woorder.payment.domain.Payment;
import com.bamin.woorder.payment.domain.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(final PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    Payment createPayment(final Payment payment) {
        return paymentRepository.save(payment);
    }
}
