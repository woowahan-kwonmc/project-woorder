package com.bamin.woorder.member.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/login")
    public String loginView() {
        return "login";
    }

    @GetMapping("/signup")
    public String signUpView() {
        return "signup";
    }
}
