package com.bamin.woorder.coupontype.application;

import com.bamin.woorder.common.dto.ResponseData;
import com.bamin.woorder.common.dto.ResponseDto;
import com.bamin.woorder.coupontype.domain.CouponType;
import com.bamin.woorder.coupontype.dto.CouponTypeCreateRequestDto;
import com.bamin.woorder.coupontype.dto.CouponTypeResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponTypeCRUDService {

    private final CouponTypeService couponTypeService;

    @Autowired
    public CouponTypeCRUDService(final CouponTypeService couponTypeService) {
        this.couponTypeService = couponTypeService;
    }


    public ResponseDto createCouponType(final CouponTypeCreateRequestDto requestDto) {
        CouponType savedCouponType = couponTypeService.createCouponType(requestDto.getCouponTypeName(), requestDto.getCouponTypeCount(),
                requestDto.getCouponTypeDiscount(), requestDto.getStartTime(), requestDto.getEndTime());
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

    private CouponTypeResponseDto mapCouponTypeResponseDto(final CouponType couponType) {
        return CouponTypeResponseDto.builder()
                .no(couponType.getNo())
                .name(couponType.getName())
                .discount(couponType.getDiscount())
                .count(couponType.getCount())
                .startTime(couponType.getStartTime())
                .endTime(couponType.getEndTime())
                .build();
    }
}
