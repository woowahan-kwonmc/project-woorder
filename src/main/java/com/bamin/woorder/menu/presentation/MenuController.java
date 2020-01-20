package com.bamin.woorder.menu.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @GetMapping("/enroll")
    public String enrollMenu() {
        return "menu-enroll";
    }

    @GetMapping("/edit")
    public String editMenu() {
        return "menu-enroll";
    }
}
