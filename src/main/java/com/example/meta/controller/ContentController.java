package com.example.meta.controller;

import com.example.meta.account.domain.MetaAccount;
import com.example.meta.account.service.MetaAccountService;
import com.example.meta.configuration.MetaConfiguration;
import com.example.meta.uitils.MetaAccountUtils;
import com.example.meta.uitils.MetaContentUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController // If you don't use @RestController, Spring boot will render view template.
public class ContentController {

    @Autowired
    private MetaConfiguration configuration;

    @Autowired
    private MetaContentUtils metaContentUtils;

    @Autowired
    private MetaAccountService metaAccountService;

    @GetMapping("/getMedia")
    public Map<String,Object> getMedia(@RequestParam("id") Long id) {
        Map<String,Object> response = new HashMap<>();
        try {
            MetaAccount metaAccount =  metaAccountService.read(id).orElse(null);
            if (metaAccount == null) {
                response.put("status", "error");
                response.put("message", "MetaAccount not found");
                return response;
            }

            String token = metaAccount.getToken();
            String igId = metaAccount.getInstagramId();

            Map<String,Object> data = new HashMap<>();
            data.put("access_token", token);
            data.put("limit", 20);

            Map<String,Object> result = metaContentUtils.getMedia(igId,data);
            response.put("status", "success");
            response.put("item", result);
        }catch (Exception e){
            response.put("status", "error");
            response.put("message", e.getMessage());
        }
        return response;
    }

    @PostMapping("/postSingleMedia") // feed, story, reels
    public Map<String,Object> postSingleContent(@RequestParam("id") Long id,
                                  @RequestParam("postType") String postType,
                                  @RequestParam("url") String url,
                                  @RequestParam("caption") String caption){

        Map<String,Object> response = new HashMap<>();
        try {

            MetaAccount metaAccount =  metaAccountService.read(id).orElse(null);
            if (metaAccount == null) {
                response.put("status", "error");
                response.put("message", "MetaAccount not found");
                return response;
            }

            String token = metaAccount.getToken();
            String igId = metaAccount.getInstagramId();

            Map<String,Object> data = new HashMap<>();
            data.put("access_token", token);
            data.put("postType", postType);
            data.put("url", url);
            data.put("caption", caption);


            // create container
            Map<String,Object> container = metaContentUtils.postSingleContent(igId,data);


            //Todo content upload
            Map<String,Object> result = metaContentUtils.uploadContainer(igId,data);

            response.put("status", "success");
            response.put("item", result);
        }catch (Exception e){
            response.put("status", "error");
            response.put("message", e.getMessage());
        }
        return response;
    }

    @PostMapping("/postMultiMedia") // feed, story, reels
    public Map<String,Object> postMultiContent(@RequestParam("id") Long id,
                                 @RequestParam("postType") String postType,
                                 @RequestParam("url") String url,
                                 @RequestParam("caption") String caption){

        Map<String,Object> response = new HashMap<>();
        try {
            MetaAccount metaAccount =  metaAccountService.read(id).orElse(null);
            if (metaAccount == null) {
                response.put("status", "error");
                response.put("message", "MetaAccount not found");
                return response;
            }
        }catch (Exception e){
            response.put("status", "error");
            response.put("message", e.getMessage());
        }

        return response;
    }
}
