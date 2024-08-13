package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/protected")
@RequiredArgsConstructor
public class ProtectedEndController {

    @PostMapping("/hello")
    public String hello() 
    {
        return "Hello from protected endpoint";
    }
}
