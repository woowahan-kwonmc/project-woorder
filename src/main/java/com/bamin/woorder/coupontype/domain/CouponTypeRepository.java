package com.bamin.woorder.coupontype.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface CouponTypeRepository extends JpaRepository<CouponType, Long> {
    Page<CouponType> findAllByCouponTypeHasCodeCouponTypeHasCodeAndCouponTypeUsablePeriodEndTimeIsAfter(final boolean hasCode, final LocalDateTime now, final Pageable pageable);
}
