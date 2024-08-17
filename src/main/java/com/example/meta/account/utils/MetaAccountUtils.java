package com.example.meta.account.utils;

import com.example.meta.configuration.MetaConfiguration;
import com.example.meta.feign.MetaFeignClient;
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

            Map<String, Object> data = new HashMap<>();
            data.put("grant_type", "fb_exchange_token");
            data.put("client_id", configuration.getClientId());
            data.put("client_secret", configuration.getClientSecret());
            data.put("fb_exchange_token", token);

            Map<String, Object> response = metaFeignClient.callGet("oauth/access_token", data);
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
            Map<String, Object> data = new HashMap<>();
            data.put("access_token", token);
            Map<String, Object> response = metaFeignClient.callGet("me/accounts", data);
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
            Map<String,Object> data = new HashMap<>();
            String fields = "name,instagram_business_account";
            data.put("fields", fields);
            data.put("access_token", token);
            Map<String, Object> result = metaFeignClient.callGet(pageId, data);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getPageAccessToken(String pageId, String token){
        Map<String, Object> data = new HashMap<>();
        data.put("access_token", token);
        data.put("fields", "access_token");
        Map<String, Object> result = metaFeignClient.callGet(pageId ,data);
        return (String) result.get("access_token");
    }
}
