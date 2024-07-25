package com.example.meta.uitils;

import com.example.meta.feign.MetaFeignClient;
import com.example.meta.configuration.MetaConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MetaContentUtils {

    @Autowired
    private MetaConfiguration configuration;
    @Autowired
    private MetaFeignClient metaFeignClient;

    public Map<String, Object> getMedia(String pageId, Map<String, Object> data) {
        try {
            String uri = pageId +"/media";

            Integer limit = (Integer) data.get("limit");
//            String token = (String)data.get("access_token");
            String fields = "id,media_type,media_url,permalink,media_product_type";
            data.put("fields", fields);
//            data.put("access_token", token);
            data.put("limit", limit);

            Map<String, Object> result = metaFeignClient.callGet(uri ,data);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
