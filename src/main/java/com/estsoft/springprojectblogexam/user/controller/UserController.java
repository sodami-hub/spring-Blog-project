package com.estsoft.springprojectblogexam.user.controller;

import com.estsoft.springprojectblogexam.user.entity.dto.AddUserRequest;
import com.estsoft.springprojectblogexam.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    UserController(UserService ser) {
        this.service=ser;
    }

    @PostMapping()
    public String save(@ModelAttribute AddUserRequest request) {
        //@ModelAttribute => Model + @RequestBody
        service.save(request);
        return "redirection:/login";  // 회원가입이후에 login 페이지로 이동.
    }
}
