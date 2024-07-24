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

    public Map<String, Object> getMedia(String pageId, String token) {
        try {
            String uri = pageId +"/media";
            Map<String, Object> result = metaFeignClient.callGet(uri, token, "id,media_type,media_url,permalink,media_product_type");
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
