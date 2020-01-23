package com.bamin.woorder.payment.application;

import com.bamin.woorder.payment.domain.Payment;
import com.bamin.woorder.payment.domain.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    List<Payment> selectPagePayment(final Long memberNo, final int page, final int num) {
        return paymentRepository.findAllByPaymentMemberMemberMemberNo(memberNo, PageRequest.of(page - 1, num))
                .stream()
                .collect(Collectors.toList());
    }
}
