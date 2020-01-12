package com.bamin.woorder.coupontype.application;

import com.bamin.woorder.coupontype.domain.CouponType;
import com.bamin.woorder.coupontype.domain.CouponTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponTypeService {

    private final CouponTypeRepository couponTypeRepository;

    @Autowired
    public CouponTypeService(final CouponTypeRepository couponTypeRepository) {
        this.couponTypeRepository = couponTypeRepository;
    }

    public CouponType createCouponType(final String couponTypeName,
                                       final String couponTypeCount,
                                       final String couponTypeDiscount,
                                       final String startTime,
                                       final String endTime) {
        CouponType couponType = CouponType.builder()
                .couponTypeName(couponTypeName)
                .couponTypeCount(couponTypeCount)
                .couponTypeDiscount(couponTypeDiscount)
                .startTime(startTime)
                .endTime(endTime)
                .build();
        return couponTypeRepository.save(couponType);
    }
}
