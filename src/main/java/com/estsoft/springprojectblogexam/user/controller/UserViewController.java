package com.estsoft.springprojectblogexam.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserViewController {
    // GET /login -> login 페이지

    @GetMapping("/login")
    public String login() {
        return "/user/login";
    }

    // GET /signup -> 회원가입 페이지
    @GetMapping("/signup")
    public String signUp() {
        return "user/signup";
    }
}
