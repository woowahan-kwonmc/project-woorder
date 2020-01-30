package com.bamin.woorder.common.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CouponCodeGeneratorTest {

    @Test
    @DisplayName("쿠폰 코드 생성기 테스트")
    void couponCodeGenerateCheckDifference() {
        String code1 = CouponCodeGenerator.generate("안녕", 1L);
        String code2 = CouponCodeGenerator.generate("안녕", 2L);
        assertNotEquals(code1, code2);
    }

    @Test
    @DisplayName("쿠폰 코드 생성기 1천만개 테스트")
    void couponDuplicatedCheckWithSameName() {
        Set<String> couponCodes = getCouponCodeSet(1L, 10_000_000L, "안녕");

        int targetGeneratedCouponCodeCount = 10_000_000;

        assertThat(couponCodes.size()).isEqualTo(targetGeneratedCouponCodeCount);
    }

    private HashSet<String> getCouponCodeSet(final long startCouponNo, final long endCouponNo, final String couponName) {
        return LongStream.rangeClosed(startCouponNo, endCouponNo)
                .parallel()
                .collect(HashSet::new,
                        (set, fakeCouponNo) -> set.add(CouponCodeGenerator.generate(couponName, fakeCouponNo)),
                        Set::addAll);
    }
}