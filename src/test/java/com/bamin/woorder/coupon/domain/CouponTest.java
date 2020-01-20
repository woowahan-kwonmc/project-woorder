package com.bamin.woorder.coupon.domain;

import com.bamin.woorder.member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static com.bamin.woorder.coupontype.CouponTypeConstants.TEST_COUPON_TYPE_DISCOUNT_1000;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CouponTest {

    private Member couponOwnMember;
    private Member couponNotOwnMember;

    @BeforeEach
    void setUp() {
        couponOwnMember = new Member("쿠폰소유자");
        ReflectionTestUtils.setField(couponOwnMember, "memberNo", 1L);
        couponNotOwnMember = new Member("쿠폰안소유자");
        ReflectionTestUtils.setField(couponNotOwnMember, "memberNo", 2L);
    }

    @Test
    @DisplayName("쿠폰 생성 성공")
    void successfullyConstructCoupon() {
        assertDoesNotThrow(() -> new Coupon(TEST_COUPON_TYPE_DISCOUNT_1000));
    }

    @Test
    @DisplayName("이미 사용한 쿠폰 사용 실패")
    void failedUseCouponAlreadyUsed() {
        Coupon newCoupon = new Coupon(TEST_COUPON_TYPE_DISCOUNT_1000);
        ReflectionTestUtils.setField(newCoupon, "couponMember", new CouponMember(couponOwnMember));

        newCoupon.useCoupon(couponOwnMember);

        assertThrows(AlreadyUsedCouponException.class, () -> newCoupon.useCoupon(couponOwnMember));
    }

    @Test
    @DisplayName("다른 멤버의 쿠폰 사용 실패")
    void failedUseCouponOwnByAnotherMember() {
        Coupon newCoupon = new Coupon(TEST_COUPON_TYPE_DISCOUNT_1000);
        ReflectionTestUtils.setField(newCoupon, "couponMember", new CouponMember(couponOwnMember));

        assertThrows(InvalidCouponOwnerException.class, () -> newCoupon.useCoupon(couponNotOwnMember));
    }
}