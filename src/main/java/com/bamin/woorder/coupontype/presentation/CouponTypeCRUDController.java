package com.bamin.woorder.coupontype.presentation;

import com.bamin.woorder.coupontype.application.CouponTypeCRUDService;
import com.bamin.woorder.coupontype.dto.CouponTypeCreateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CouponTypeCRUDController {

    private final CouponTypeCRUDService couponTypeCRUDService;

    @Autowired
    public CouponTypeCRUDController(final CouponTypeCRUDService couponTypeCRUDService) {
        this.couponTypeCRUDService = couponTypeCRUDService;
    }

    @PostMapping("/couponTypes")
    public ResponseEntity createCouponType(@RequestBody CouponTypeCreateRequestDto requestDto) {
        return ResponseEntity.ok(couponTypeCRUDService.createCouponType(requestDto));
    }
}
