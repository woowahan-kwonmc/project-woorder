package com.bamin.woorder.coupontype.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/coupontype")
public class CouponTypeController {

    @GetMapping
    public String index() {
        return "admin/coupontype/index";
    }

    @GetMapping("/create")
    public String create() {
        return "admin/coupontype/create/index";
    }

    @GetMapping("/select")
    public String select() {
        return "admin/coupontype/select/index";
    }
}
