package com.bamin.woorder.coupon.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CouponPageReadRequestDto {

    private int page;
    private int num;
    private Boolean usable;
    private Boolean expired;

    public CouponPageReadRequestDto(final int page, final int num) {
        this.page = page;
        this.num = num;
        this.usable = false;
        this.expired = false;
    }

    public CouponPageReadRequestDto(final int page, final int num, final Boolean usable, final Boolean expired) {
        this.page = page;
        this.num = num;
        this.usable = usable;
        this.expired = expired;
    }

    public CouponPageReadRequestDto(final int page, final int num, final Boolean expired) {
        this.page = page;
        this.num = num;
        this.usable = false;
        this.expired = expired;
    }
}
