package com.bamin.woorder.coupon.application;

import com.bamin.woorder.coupon.domain.Coupon;
import com.bamin.woorder.coupon.domain.CouponRepository;
import com.bamin.woorder.coupontype.domain.CouponType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Service
public class CouponService {

    private final CouponRepository couponRepository;

    @Autowired
    public CouponService(final CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    Long countCouponIncludedCouponType(final Long couponTypeNo) {
        return couponRepository.countAllByCouponTypeInfoCouponTypeCouponTypeNo(couponTypeNo);
    }

    List<Coupon> createCoupons(final CouponType couponType, final Long requestCounts) {
        return LongStream.rangeClosed(1, requestCounts)
                .boxed()
                .map(aLong -> couponRepository.save(new Coupon(couponType)))
                .collect(Collectors.toList());
    }
}
