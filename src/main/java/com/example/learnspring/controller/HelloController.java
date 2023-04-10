package com.example.learnspring.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
public class HelloController {

    @GetMapping("hello/{name}")
    public String hello(@PathVariable String name) {
        return "Hello " + name;
    }
}
