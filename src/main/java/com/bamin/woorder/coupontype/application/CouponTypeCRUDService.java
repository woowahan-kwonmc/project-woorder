package com.bamin.woorder.coupontype.application;

import com.bamin.woorder.common.dto.ResponseData;
import com.bamin.woorder.common.dto.ResponseDto;
import com.bamin.woorder.coupon.domain.Coupon;
import com.bamin.woorder.coupontype.domain.CouponType;
import com.bamin.woorder.coupontype.dto.CouponTypeCreateRequestDto;
import com.bamin.woorder.coupontype.dto.CouponTypeResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CouponTypeCRUDService {

    private final CouponTypeService couponTypeService;

    @Autowired
    public CouponTypeCRUDService(final CouponTypeService couponTypeService) {
        this.couponTypeService = couponTypeService;
    }


    public ResponseDto createCouponType(final CouponTypeCreateRequestDto requestDto) {
        CouponType savedCouponType = couponTypeService.createCouponTypeBuilder()
                .couponTypeName(requestDto.getName())
                .couponTypeCount(requestDto.getCount())
                .couponTypeDiscount(requestDto.getDiscount())
                .couponTypeHasCode(requestDto.isHasCode())
                .startTime(requestDto.getStartTime())
                .endTime(requestDto.getEndTime())
                .build();
        return ResponseDto.builder()
                .path("/api/v1/couponTypes")
                .method("POST")
                .message("쿠폰 종류 생성 성공")
                .statusCode(200)
                .data(ResponseData.builder()
                        .insert("couponType", mapCouponTypeResponseDto(savedCouponType))
                        .build())
                .build();
    }

    public ResponseDto selectPageCouponTypes(final int page, final int num) {
        List<CouponType> couponTypes = couponTypeService.selectPageCouponTypes(page, num);
        List<CouponTypeResponseDto> pageCouponTypes = couponTypes.stream()
                .map(couponType -> {
                    return mapCouponTypeResponseDto(couponType);
                })
                .collect(Collectors.toList());
        return ResponseDto.builder()
                .path("/api/v1/couponTypes/all")
                .method("GET")
                .message(String.format("쿠폰 타입 %d 페이지 %d 개 조회 성공", page, num))
                .statusCode(200)
                .data(ResponseData.builder()
                        .insert("couponTypes", pageCouponTypes)
                        .build())
                .build();
    }

    public ResponseDto selectCouponType(final Long couponTypeNo) {
        CouponType savedCouponType = couponTypeService.selectCouponType(couponTypeNo);
        List<Coupon> couponTypeCoupons = savedCouponType.getCoupons();
        return ResponseDto.builder()
                .path(String.format("/api/v1/couponTypes/%d", couponTypeNo))
                .method("GET")
                .message("쿠폰 타입 정보 조회 성공")
                .statusCode(200)
                .data(ResponseData.builder()
                        .insert("couponType", mapCouponTypeResponseDto(savedCouponType))
                        .insert("couponsSize", couponTypeCoupons.size())
                        .build())
                .build();
    }

    private CouponTypeResponseDto mapCouponTypeResponseDto(final CouponType couponType) {
        return CouponTypeResponseDto.builder()
                .no(couponType.getNo())
                .name(couponType.getName())
                .hasCode(couponType.getHasCode())
                .discount(couponType.getDiscount())
                .count(couponType.getCount())
                .startTime(couponType.getStartTime())
                .endTime(couponType.getEndTime())
                .build();
    }
}
