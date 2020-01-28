package com.bamin.woorder.order.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

    @GetMapping
    public String index() {
        return "admin/order/index";
    }

    @GetMapping("/select/all")
    public String selectAll() {
        return "admin/order/select/all/index";
    }
}
