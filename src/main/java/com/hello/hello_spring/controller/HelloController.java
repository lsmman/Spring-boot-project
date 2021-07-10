package com.hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    // static 정적 사이트
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "lsmman!!");
        // template 의 hello 로 연결
        return "hello";
    }

    // MVC
    @GetMapping("hello-mvc")
    public String hello(@RequestParam(value = "name", required = false) String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    // API return str
    // controller가 string을 리턴하면,
    // HttpMessageConverter 의 StringHttpMessageConverter 가 동작
    @GetMapping("hello-string")
    @ResponseBody // 리스폰스 바디에 이 값을 직접 넣어주겠다. return 값이 html 문서 만들어질 필요 없이 출력됨
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    // API return Object -> Json (default)
    // controller가 object를 리턴하면,
    // HttpMessageConverter 의 MappingJackson2HttpMessageConverter 가 json으로 만들어줌
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

