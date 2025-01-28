package com.example.springsecurityjwt;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloResource {

    @GetMapping("/hello")
    public String hello() {
        System.out.println("At hello");
        return "Hello World";
    }
}
