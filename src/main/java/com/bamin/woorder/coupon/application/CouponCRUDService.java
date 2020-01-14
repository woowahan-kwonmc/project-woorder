package com.bamin.woorder.coupon.application;

import com.bamin.woorder.common.dto.ResponseDto;
import com.bamin.woorder.coupon.domain.Coupon;
import com.bamin.woorder.coupon.dto.CouponCreateRequestDto;
import com.bamin.woorder.coupon.dto.CouponData;
import com.bamin.woorder.coupon.dto.CouponResponseDto;
import com.bamin.woorder.coupontype.application.CouponTypeService;
import com.bamin.woorder.coupontype.domain.CouponType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CouponCRUDService {

    private final CouponService couponService;
    private final CouponTypeService couponTypeService;

    @Autowired
    public CouponCRUDService(CouponService couponService, CouponTypeService couponTypeService) {
        this.couponService = couponService;
        this.couponTypeService = couponTypeService;
    }

    public ResponseDto createDownloadableCoupons(final Long couponTypeNo, final Long requestCounts) {
        CouponType couponType = getDownloadableCouponType(couponTypeNo);
        Long currentCouponTypeCount = couponService.countCouponIncludedCouponType(couponType.getCouponTypeNo());
        Long totalCouponTypeCount = couponType.getTotalCountAfterGenerate(currentCouponTypeCount, requestCounts);
        List<Coupon> createdCoupons = couponService.createCoupons(couponType, requestCounts);
        return ResponseDto.builder()
                .path("/api/v1/coupons/downloadMode")
                .method("POST")
                .message("다운로드 쿠폰 생성 성공")
                .statusCode(200)
                .data(mapToCouponResponseDto(couponType, totalCouponTypeCount, mapToCouponData(createdCoupons)))
                .build();
    }

    private CouponType getDownloadableCouponType(final Long couponTypeNo) {
        CouponType couponType = couponTypeService.selectCreatableCouponType(couponTypeNo);
        couponType.checkDownloadable();
        return couponType;
    }

    private List<CouponData> mapToCouponData(final List<Coupon> createdCoupons) {
        return createdCoupons.stream()
                .map(coupon -> new CouponData(coupon.getCode(), coupon.getUseStatus()))
                .collect(Collectors.toList());
    }

    private CouponResponseDto mapToCouponResponseDto(final CouponType couponType,
                                                     final Long totalCouponTypeCount,
                                                     final List<CouponData> coupons) {
        return CouponResponseDto.builder()
                .coupons(coupons)
                .couponTypeName(couponType.getName())
                .couponTypeDiscount(couponType.getDiscount())
                .couponTypeCurrentCount(totalCouponTypeCount)
                .couponTypeCount(couponType.getCount())
                .couponTypeHasCode(couponType.getHasCode())
                .couponTypeStartTime(couponType.getStartTime())
                .couponTypeEndTime(couponType.getEndTime())
                .build();
    }
}
