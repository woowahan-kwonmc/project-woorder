package com.bamin.woorder.coupontype.presentation;

import com.bamin.woorder.common.dto.PageReadRequestDto;
import com.bamin.woorder.common.presentation.PageRequestParams;
import com.bamin.woorder.coupontype.application.CouponTypeCRUDService;
import com.bamin.woorder.coupontype.dto.CouponTypeCreateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CouponTypeCRUDController {

    private final CouponTypeCRUDService couponTypeCRUDService;

    @Autowired
    public CouponTypeCRUDController(final CouponTypeCRUDService couponTypeCRUDService) {
        this.couponTypeCRUDService = couponTypeCRUDService;
    }

    @PostMapping("/couponTypes")
    public ResponseEntity createCouponType(@RequestBody final CouponTypeCreateRequestDto requestDto) {
        return ResponseEntity.ok(couponTypeCRUDService.createCouponType(requestDto));
    }

    @GetMapping("/couponTypes/all")
    public ResponseEntity selectPageCouponTypes(@PageRequestParams final PageReadRequestDto requestDto) {
        return ResponseEntity.ok(couponTypeCRUDService.selectPageCouponTypes(requestDto.getPage(), requestDto.getNum()));
    }

    @GetMapping("/couponTypes/{couponTypeNo}")
    public ResponseEntity selectCouponType(@PathVariable final Long couponTypeNo) {
        return ResponseEntity.ok(couponTypeCRUDService.selectCouponType(couponTypeNo));
    }
}
