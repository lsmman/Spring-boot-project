package com.hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    // static index.html과 controller "/" 우선순위
    // controller에서 / 링크를 먼저 찾고 없으면 static 에서 index.html을 찾는다.
    @GetMapping("/")
    public String home() {
        return "home";
    }

}
