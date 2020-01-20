package com.bamin.woorder.common.utils;

import com.bamin.woorder.common.exception.CouponCodeGenerateException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CouponCodeGenerator {

    private static final String ALGORITHM_SHA_1 = "SHA-1";

    public static String generate(final String couponTypeName, final Long couponNo) {
        try {
            MessageDigest sha1 = getMessageDigest(generateBaseString(couponTypeName, couponNo));
            String code = String.format("%040x", new BigInteger(1, sha1.digest())).substring(0, 12);
            log.info("Generated Code : {}", code);
            return code.toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            throw new CouponCodeGenerateException();
        }
    }

    private static MessageDigest getMessageDigest(final String baseCouponCodeString) throws NoSuchAlgorithmException {
        MessageDigest sha1 = MessageDigest.getInstance(ALGORITHM_SHA_1);
        sha1.reset();
        sha1.update(baseCouponCodeString.getBytes(StandardCharsets.UTF_8));
        return sha1;
    }

    private static String generateBaseString(final String couponTypeName, final Long couponNo) {
        return couponTypeName + couponNo;
    }
}
