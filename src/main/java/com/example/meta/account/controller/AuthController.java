package com.example.meta.account.controller;


import com.example.meta.configuration.MetaConfiguration;
import com.example.meta.account.domain.MetaAccount;
import com.example.meta.account.service.MetaAccountService;
import com.example.meta.account.utils.MetaAccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private MetaAccountService metaAccountService;

    @GetMapping("/")
    public String index(Model model){

        model.addAttribute("facebookAppId", configuration.getClientId());
        return "index";
    }

    @PostMapping("/login")
    public void login(@RequestBody Map<String, Object> payload) {
        String accessToken = (String) payload.get("accessToken");
        String token = metaAccountUtils.getLongTermToken(accessToken);
        Map<String, Object> page = metaAccountUtils.getPage(token);
        Map<String, Object> profile = metaAccountUtils.getProfile((String)page.get("id"),accessToken);
        Map<String, Object> igProfile = (Map<String, Object>) profile.get("instagram_business_account");

        MetaAccount metaAccount = new MetaAccount();
        metaAccount.setName((String)profile.get("name"));
        metaAccount.setInstagramId((String)igProfile.get("id"));
        metaAccount.setToken(token);
        metaAccount.setPageId((String)page.get("id"));

        MetaAccount savedAccount = metaAccountService.save(metaAccount);
        System.out.println("metaAccount : "+ metaAccount.getId());
        System.out.println("savedAccount : "+ savedAccount.getId());
    }


    
}
