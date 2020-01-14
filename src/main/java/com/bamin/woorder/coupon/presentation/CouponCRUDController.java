package com.bamin.woorder.coupon.presentation;

import com.bamin.woorder.common.utils.RangeUtils;
import com.bamin.woorder.coupon.application.CouponCRUDService;
import com.bamin.woorder.coupon.application.Mode;
import com.bamin.woorder.coupon.dto.CouponCreateRequestDto;
import com.bamin.woorder.coupon.dto.CouponPageReadRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CouponCRUDController {

    private final CouponCRUDService couponCRUDService;

    @Autowired
    public CouponCRUDController(final CouponCRUDService couponCRUDService) {
        this.couponCRUDService = couponCRUDService;
    }

    @PostMapping("/coupons/downloadMode")
    public ResponseEntity createDownloadableCoupons(@RequestBody final CouponCreateRequestDto requestDto) {
        return ResponseEntity.ok(couponCRUDService.createDownloadableCoupons(requestDto.getCouponTypeNo(),
                RangeUtils.checkPositive(requestDto.getRequestCounts()), Mode.DOWNLOAD));
    }

    @PostMapping("/coupons/codeMode")
    public ResponseEntity createCodeCoupons(@RequestBody final CouponCreateRequestDto requestDto) {
        return ResponseEntity.ok(couponCRUDService.createCodeCoupons(requestDto.getCouponTypeNo(),
                RangeUtils.checkPositive(requestDto.getRequestCounts()), Mode.CODE));
    }

    @GetMapping("/coupons")
    public ResponseEntity selectPageCoupons(@CouponPageRequestParams final CouponPageReadRequestDto requestDto) {
        return ResponseEntity.ok(couponCRUDService.selectPageCoupons(requestDto));
    }
}
