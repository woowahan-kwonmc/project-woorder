package com.bamin.woorder.coupon.presentation;

import com.bamin.woorder.common.utils.RangeUtils;
import com.bamin.woorder.coupon.application.CouponCRUDService;
import com.bamin.woorder.coupon.application.Mode;
import com.bamin.woorder.coupon.dto.CouponCreateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CouponCRUDController {

    private final CouponCRUDService couponCRUDService;

    @Autowired
    public CouponCRUDController(final CouponCRUDService couponCRUDService) {
        this.couponCRUDService = couponCRUDService;
    }

    @PostMapping("/coupons/downloadMode")
    public ResponseEntity createDownloadableCoupons(@RequestBody CouponCreateRequestDto requestDto) {
        return ResponseEntity.ok(couponCRUDService.createDownloadableCoupons(requestDto.getCouponTypeNo(),
                RangeUtils.checkPositive(requestDto.getRequestCounts()), Mode.DOWNLOAD));
    }

    @PostMapping("/coupons/codeMode")
    public ResponseEntity createCodeCoupons(@RequestBody CouponCreateRequestDto requestDto) {
        return ResponseEntity.ok(couponCRUDService.createCodeCoupons(requestDto.getCouponTypeNo(),
                RangeUtils.checkPositive(requestDto.getRequestCounts()), Mode.CODE));
    }
}
