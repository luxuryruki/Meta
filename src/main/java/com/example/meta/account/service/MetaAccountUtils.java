package com.example.meta.account.service;

import com.example.meta.account.config.MetaConfiguration;
import com.example.meta.account.feign.MetaFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MetaAccountUtils {
    @Autowired
    private MetaConfiguration configuration;


    @Autowired
    private MetaFeignClient metaFeignClient;

    public String getLongTermToken(String token) {
        try {
            String apiUrl = configuration.getGraphUrlPrefix() + "/oauth/access_token";

            Map<String, Object> data = new HashMap<>();
            data.put("grant_type", "fb_exchange_token");
            data.put("client_id", configuration.getClientId());
            data.put("client_secret", configuration.getClientSecret());
            data.put("fb_exchange_token", token);

            Map<String, Object> response = metaFeignClient.callGetWithBody("oauth/access_token", data);
            String longTermToken = (String)response.get("access_token");
            return longTermToken;
//            List<Map<String,Object>> dataList = (List<Map<String,Object>>) resultMap.get("data");
//            return dataList.get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Get Facebook page info
    public Map<String, Object> getPage(String token) {
        try {
            String apiUrl = configuration.getGraphUrlPrefix() + "/me/accounts";

            Map<String, Object> response = metaFeignClient.callGetWithParam("me/accounts", token);
            List<Map<String,Object>> dataList = (List<Map<String,Object>>)response.get("data");
            return dataList.get(0);
//            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Get Instagram profile
    public Map<String, Object> getProfile(String pageId, String token) {
        try {
            String apiUrl = configuration.getGraphUrlPrefix() + "/" + pageId;
//            Map<String, Object> data = new HashMap<>();
//            data.put("fields", "name,instagram_business_account");
            Map<String, Object> result = metaFeignClient.callGet(pageId, token, "name,instagram_business_account");
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
