package com.bamin.woorder.coupon.presentation;

import com.bamin.woorder.common.utils.RangeUtils;
import com.bamin.woorder.coupon.application.CouponCRUDService;
import com.bamin.woorder.coupon.application.Mode;
import com.bamin.woorder.coupon.dto.CouponCreateRequestDto;
import com.bamin.woorder.coupon.dto.CouponDownloadRequestDto;
import com.bamin.woorder.coupon.dto.CouponEnrollRequestDto;
import com.bamin.woorder.coupon.dto.CouponPageReadRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin({"http://localhost:8081", "http://localhost:3000"})
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

    @PutMapping("/coupons/codeMode")
    public ResponseEntity enrollCodeCoupon(@RequestBody final CouponEnrollRequestDto requestDto) {
        return ResponseEntity.ok(couponCRUDService.enrollCodeCoupon(requestDto));
    }

    @PutMapping("/coupons/downloadMode")
    public ResponseEntity enrollDownloadCoupon(@RequestBody final CouponDownloadRequestDto requestDto) {
        return ResponseEntity.ok(couponCRUDService.enrollDownloadCoupon(requestDto));
    }

    @GetMapping("/coupons/{couponNo}")
    public ResponseEntity findCouponByNo(@PathVariable final Long couponNo) {
        return ResponseEntity.ok(couponCRUDService.findCouponByCouponNo(couponNo));
    }

    @GetMapping("/coupons/code/{couponCode}")
    public ResponseEntity findCouponByCode(@PathVariable final String couponCode) {
        return ResponseEntity.ok(couponCRUDService.findCouponByCode(couponCode));
    }

    @GetMapping("/coupons/code")
    public ResponseEntity existsByCode(@RequestParam final String couponCode) {
        return ResponseEntity.ok(couponCRUDService.existsCouponByCode(couponCode));
    }

    @GetMapping("/coupons")
    public ResponseEntity selectPageCoupons(@CouponPageRequestParams final CouponPageReadRequestDto requestDto) {
        return ResponseEntity.ok(couponCRUDService.selectPageCoupons(requestDto));
    }

    @GetMapping("/coupons/members/{memberNo}")
    public ResponseEntity readMemberCoupons(@PathVariable final Long memberNo) {
        return ResponseEntity.ok(couponCRUDService.readMemberCoupons(memberNo));
    }
}
