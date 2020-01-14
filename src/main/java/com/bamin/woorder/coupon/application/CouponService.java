package com.bamin.woorder.coupon.application;

import com.bamin.woorder.common.utils.CouponCodeGenerator;
import com.bamin.woorder.coupon.domain.Coupon;
import com.bamin.woorder.coupon.domain.CouponRepository;
import com.bamin.woorder.coupontype.domain.CouponType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    List<Coupon> createDownloadableCoupons(final CouponType couponType, final Long requestCounts) {
        return LongStream.rangeClosed(1, requestCounts)
                .boxed()
                .map(aLong -> couponRepository.save(new Coupon(couponType)))
                .collect(Collectors.toList());
    }

    List<Coupon> createCodeCoupons(final CouponType couponType, final Long requestCounts) {
        Long couponCount = couponRepository.count();
        return LongStream.rangeClosed(1, requestCounts)
                .boxed()
                .map(count -> {
                    try {
                        String code = CouponCodeGenerator.generate(couponType.getName(), couponCount + count);
                        return saveCoupon(couponType, code);
                    } catch (CouponCodeDuplicatedException e) {
                        String alternativeCouponName = couponType.getName() + Long.toHexString(couponCount);
                        String alternativeCode = CouponCodeGenerator.generate(alternativeCouponName, couponCount + count);
                        return saveCoupon(couponType, alternativeCode);
                    }
                })
                .collect(Collectors.toList());
    }

    private Coupon saveCoupon(final CouponType couponType, final String alternativeCode) {
        try {
            return couponRepository.save(new Coupon(alternativeCode, couponType));
        } catch (DataIntegrityViolationException e) {
            throw new CouponCodeDuplicatedException();
        }
    }
}
