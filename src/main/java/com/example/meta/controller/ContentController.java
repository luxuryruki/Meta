package com.example.meta.controller;

import com.example.meta.account.domain.MetaAccount;
import com.example.meta.account.service.MetaAccountService;
import com.example.meta.configuration.MetaConfiguration;
import com.example.meta.uitils.MetaAccountUtils;
import com.example.meta.uitils.MetaContentUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Controller
public class ContentController {

    @Autowired
    private MetaConfiguration configuration;

    @Autowired
    private MetaContentUtils metaContentUtils;

    @Autowired
    private MetaAccountService metaAccountService;

    @GetMapping("/getMedia")
    public Map<String, Object> login(HttpServletRequest request) {
        Long id = Long.valueOf(request.getParameter("id")) ;
        MetaAccount metaAccount =  metaAccountService.read(id).orElse(null);
        String token = metaAccount.getToken();
        String igId = metaAccount.getInstagramId();

        Map<String, Object>  contents = metaContentUtils.getMedia(igId,token);

        return contents;
    }
}
