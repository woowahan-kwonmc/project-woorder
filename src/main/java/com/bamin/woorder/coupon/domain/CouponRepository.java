package com.bamin.woorder.coupon.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Long countAllByCouponTypeInfoCouponTypeCouponTypeNo(final Long couponTypeNo);

    Page<Coupon> findAllByCouponUseStatusCouponStatus(final CouponStatus couponStatus, final Pageable pageable);

    Page<Coupon> findAllByCouponUseStatusCouponStatusAndCouponTypeInfoCouponTypeCouponTypeUsablePeriodEndTimeIsAfter(final CouponStatus couponStatus, final LocalDateTime currentTime, final Pageable pageable);

    Page<Coupon> findAllByCouponTypeInfoCouponTypeCouponTypeUsablePeriodEndTimeIsBefore(final LocalDateTime currentTime, final Pageable pageable);

    Optional<Coupon> findByCouponCodeCouponCode(final String couponCode);

    boolean existsByCouponCodeCouponCode(String couponCode);

    List<Coupon> findAllByCouponUseStatusCouponStatusAndCouponMemberCouponMemberMemberNo(final CouponStatus couponStatus, final Long memberNo);
}
