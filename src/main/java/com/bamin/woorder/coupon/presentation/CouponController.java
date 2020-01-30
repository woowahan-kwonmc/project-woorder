package com.bamin.woorder.coupon.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/coupon")
public class CouponController {

    @GetMapping("/select")
    public String select() {
        return "admin/coupon/select/index";
    }
}
