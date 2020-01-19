package com.bamin.woorder.coupon.application;

import com.bamin.woorder.common.dto.ResponseData;
import com.bamin.woorder.common.dto.ResponseDto;
import com.bamin.woorder.coupon.domain.Coupon;
import com.bamin.woorder.coupon.dto.*;
import com.bamin.woorder.coupontype.application.CouponTypeService;
import com.bamin.woorder.coupontype.domain.CouponType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public ResponseDto createDownloadableCoupons(final Long couponTypeNo, final Long requestCounts, final Mode mode) {
        CouponType couponType = getDownloadableCouponType(couponTypeNo);
        Long currentCouponTypeCount = couponService.countCouponIncludedCouponType(couponType.getCouponTypeNo());
        Long totalCouponTypeCount = couponType.getTotalCountAfterGenerate(currentCouponTypeCount, requestCounts);
        List<Coupon> createdCoupons = couponService.createDownloadableCoupons(couponType, requestCounts);
        return ResponseDto.builder()
                .path(String.format("/api/v1/coupons%s", mode.getPath()))
                .method("POST")
                .message(String.format("%s 쿠폰 생성 성공", mode.getName()))
                .statusCode(200)
                .data(mapToCouponResponseDto(couponType, totalCouponTypeCount, mapToCouponData(createdCoupons)))
                .build();
    }

    private CouponType getDownloadableCouponType(final Long couponTypeNo) {
        CouponType couponType = couponTypeService.selectCreatableCouponType(couponTypeNo);
        couponType.checkDownloadable();
        return couponType;
    }

    public ResponseDto createCodeCoupons(final Long couponTypeNo, final Long requestCounts, final Mode mode) {
        CouponType couponType = getCodeCouponType(couponTypeNo);
        Long currentCouponTypeCount = couponService.countCouponIncludedCouponType(couponType.getCouponTypeNo());
        Long totalCouponTypeCount = couponType.getTotalCountAfterGenerate(currentCouponTypeCount, requestCounts);
        List<Coupon> createdCoupons = couponService.createCodeCoupons(couponType, requestCounts);
        return ResponseDto.builder()
                .path(String.format("/api/v1/coupons%s", mode.getPath()))
                .method("POST")
                .message(String.format("%s 쿠폰 생성 성공", mode.getName()))
                .statusCode(200)
                .data(mapToCouponResponseDto(couponType, totalCouponTypeCount, mapToCouponData(createdCoupons)))
                .build();
    }

    public ResponseDto selectPageCoupons(final CouponPageReadRequestDto requestDto) {
        List<Coupon> coupons = couponService.selectPageCouponsOnCondition(requestDto.getPage(), requestDto.getNum(),
                requestDto.getUsable(), requestDto.getExpired());
        List<CouponDescResponseDto> pageCoupons = coupons.stream()
                .map(this::mapToDescResponseDto)
                .collect(Collectors.toList());
        return ResponseDto.builder()
                .path("/api/v1/coupons")
                .method("GET")
                .message("쿠폰 목록 조회")
                .statusCode(200)
                .data(ResponseData.builder()
                        .insert("coupons", pageCoupons)
                        .insert("request", requestDto)
                        .build())
                .build();
    }

    public ResponseDto findCouponByCouponNo(final Long couponNo) {
        Coupon coupon = couponService.findByCouponNo(couponNo);
        return ResponseDto.builder()
                .path(String.format("/api/v1/coupons/%d", couponNo))
                .method("GET")
                .message("쿠폰 넘버로 조회")
                .statusCode(200)
                .data(ResponseData.builder()
                        .insert("coupon", mapToDescResponseDto(coupon))
                        .build())
                .build();
    }

    public ResponseDto findCouponByCode(final String couponCode) {
        Coupon coupon = couponService.findByCouponCode(couponCode);
        return ResponseDto.builder()
                .path(String.format("/api/v1/coupons/code/%s", couponCode))
                .method("GET")
                .message("쿠폰 코드로 조회")
                .statusCode(200)
                .data(ResponseData.builder()
                        .insert("coupon", mapToDescResponseDto(coupon))
                        .build())
                .build();
    }

    public ResponseDto existsCouponByCode(final String couponCode) {
        boolean isExists = couponService.existsByCouponCode(couponCode);
        return ResponseDto.builder()
                .path(String.format("/api/v1/coupons/code?couponCode=%s", couponCode))
                .method("GET")
                .message("쿠폰 코드 존재 여부 확인")
                .statusCode(200)
                .data(ResponseData.builder()
                        .insert("coupon", new CouponExistDto(isExists))
                        .build())
                .build();
    }

    private CouponType getCodeCouponType(final Long couponTypeNo) {
        CouponType couponType = couponTypeService.selectCreatableCouponType(couponTypeNo);
        couponType.checkHasCode();
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

    private CouponDescResponseDto mapToDescResponseDto(final Coupon coupon) {
        return CouponDescResponseDto.builder()
                .code(coupon.getCode())
                .useStatus(coupon.getUseStatus())
                .name(coupon.getName())
                .discount(coupon.getDiscount())
                .startTime(coupon.getStartTime())
                .endTime(coupon.getEndTime())
                .build();
    }
}
