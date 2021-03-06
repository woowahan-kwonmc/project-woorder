package com.bamin.woorder.payment.presentation;

import com.bamin.woorder.payment.application.PaymentCRUDService;
import com.bamin.woorder.payment.dto.PaymentCreateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
@RestController
@CrossOrigin({"http://localhost:8081", "http://localhost:3000"})
public class PaymentCRUDController {

    private PaymentCRUDService paymentCRUDService;

    @Autowired
    public PaymentCRUDController(final PaymentCRUDService paymentCRUDService) {
        this.paymentCRUDService = paymentCRUDService;
    }

    @PostMapping("/payments")
    public ResponseEntity createPayment(@RequestBody PaymentCreateRequestDto requestDto) {
        return ResponseEntity.ok(paymentCRUDService.createPayment(requestDto));
    }

    @GetMapping("/payments/members/{memberNo}")
    public ResponseEntity selectPagePayment(@PathVariable final Long memberNo,
                                            @RequestParam final int page,
                                            @RequestParam final int num) {
        return ResponseEntity.ok(paymentCRUDService.selectPagePayment(memberNo, page, num));
    }
}
