package com.bamin.woorder.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@Slf4j
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
        LocalDateTime pStart = LocalDateTime.now();
        log.debug("\n" +
                "동일 이름 자동 생성 쿠폰 코드 1천만개 충돌 테스트\n" +
                "SHA-1 방식 코드 생성, 앞 12 자리 Substring 시작 : {}", pStart.toString());

        Set<String> couponCodes = getCouponCodeSet(1L, 10_000_000L, "안녕");

        int realGeneratedCouponCodeCount = couponCodes.size();
        int targetGeneratedCouponCodeCount = 10_000_000;

        log.debug("\nReal Generated Coupon Code Count : {}", realGeneratedCouponCodeCount);
        log.debug("\nTarget Generated Coupon Code Count : {}", targetGeneratedCouponCodeCount);

        log.debug("\nCollisions Count : {}", targetGeneratedCouponCodeCount - realGeneratedCouponCodeCount);

        assertThat(couponCodes.size()).isEqualTo(targetGeneratedCouponCodeCount);
    }

    private HashSet<String> getCouponCodeSet(final long startCouponNo, final long endCouponNo, final String couponName) {
        return LongStream.rangeClosed(startCouponNo, endCouponNo)
                .parallel()
                .collect(HashSet::new, (set, fakeCouponNo) -> {
                    String code = CouponCodeGenerator.generate(couponName, fakeCouponNo);
                    set.add(code);
                }, Set::addAll);
    }
}