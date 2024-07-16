package com.example.meta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Controller
public class AuthController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/login")
    public void login(@RequestBody Map<String, Object> payload) {
        // 여기서 사용자 정보를 처리합니다.
        System.out.println("Received user data: " + payload);

    }
    
}
