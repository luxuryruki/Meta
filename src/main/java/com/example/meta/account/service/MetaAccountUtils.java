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

    public Map<String, Object> getPage(String token) {
        try {
            String apiUrl = configuration.getGraphUrlPrefix() + "/me/accounts";

            Map<String, Object> response = metaFeignClient.callGetWithParam("me/accounts", token);
            List<Map<String,Object>> dataList = (List<Map<String,Object>>)response.get("data");
//            String response = ApiUtils.callApiMethod(apiUrl, null, data, 5000, 10000, HttpMethod.GET, null);
//            Map<String, Object> resultMap = objectMapper.readValue(response, Map.class);
//            List<Map<String,Object>> dataList = (List<Map<String,Object>>) resultMap.get("data");
            return dataList.get(0);
//            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
