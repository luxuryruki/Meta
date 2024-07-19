package com.example.meta.controller;


import com.example.meta.account.config.MetaConfiguration;
import com.example.meta.account.feign.MetaFeignClient;
import com.example.meta.account.service.MetaAccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Controller
public class AuthController {

    @Autowired
    private MetaConfiguration configuration;

    @Autowired
    private MetaAccountUtils metaAccountUtils;

    @GetMapping("/")
    public String index(Model model){

        model.addAttribute("facebookAppId", configuration.getClientId());
        return "index";
    }

    @PostMapping("/login")
    public void login(@RequestBody Map<String, Object> payload) {
        // 여기서 사용자 정보를 처리합니다.
        String accessToken = (String) payload.get("accessToken");
        String token = metaAccountUtils.getLongTermToken(accessToken);
        System.out.println("Received user data: " + token);

    }
    
}
