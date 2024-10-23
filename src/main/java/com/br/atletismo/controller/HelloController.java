package com.br.atletismo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@CrossOrigin(origins="*", maxAge = 3600)
public class HelloController {

    @GetMapping
    public String hello() {
        return "Hello World";
    }
}
