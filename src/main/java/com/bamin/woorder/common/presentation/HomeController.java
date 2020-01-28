package com.bamin.woorder.common.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "user/index";
    }

    @GetMapping("/admin")
    public String adminHome() {
        return "admin/index";
    }
}
