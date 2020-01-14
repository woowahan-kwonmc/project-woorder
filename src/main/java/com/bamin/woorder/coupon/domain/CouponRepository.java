package com.bamin.woorder.coupon.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Long countAllByCouponTypeInfoCouponTypeCouponTypeNo(final Long couponTypeNo);
}
