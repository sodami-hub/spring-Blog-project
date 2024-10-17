package com.estsoft.springprojectblogexam.controller.viewcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.Arrays;

@Controller
public class PageController {

    // 아래 코드에 Spring MVC가 모두 있다. Model(처리), View(응답), Controller(요청)
    @GetMapping("/thymeleaf/example")
    public String show(Model model) {
        Person person = new Person(1L,"lee",30, Arrays.asList("running","game","watch movie"));

        model.addAttribute("person",person);
        model.addAttribute("today", LocalDateTime.now());
        return "examplePage";   // html 페이지
    }
}
