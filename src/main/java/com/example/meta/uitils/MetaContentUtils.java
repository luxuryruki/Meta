package com.example.meta.uitils;

import com.example.meta.feign.MetaFeignClient;
import com.example.meta.configuration.MetaConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
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

    public Map<String, Object> postSingleContent(String pageId, Map<String, Object> data) {
        try {
            String postType = (String) data.get("postType");
            String url =  (String) data.get("url");
            String caption =  (String) data.get("caption");
            String accessToken =  (String) data.get("access_token");


            Map<String,Object> postData = new HashMap<>();
            postData.put("caption",caption);
            postData.put("access_token", accessToken);
            switch (postType){
                case "feed":{
                    if(url.toLowerCase().endsWith(".jpeg") || url.toLowerCase().endsWith(".jpg")){
                        postData.put("image_url",url);
                    }else {
                        throw new RuntimeException("not allowed to upload the file as feed");
                    }

                    break;
                }
                case "reels":{
                    if(url.toLowerCase().endsWith(".mov") || url.toLowerCase().endsWith(".mp4") || url.toLowerCase().endsWith(".avi")){
                        postData.put("video_url",url);
                        postData.put("media_type","REELS");
                    }else {
                        throw new RuntimeException("not allowed to upload the file as reels");
                    }

                    break;
                }
                case "story":{
                    postData.put("media_type","STORIES");

                    if(url.toLowerCase().endsWith(".mov") || url.toLowerCase().endsWith(".mp4") || url.toLowerCase().endsWith(".avi")){
                        postData.put("video_url",url);
                    }else if(url.toLowerCase().endsWith(".jpeg") || url.toLowerCase().endsWith(".jpg")){
                        postData.put("image_url",url);
                    }else {
                        throw new RuntimeException("not allowed to upload the file : ");
                    }

                    break;
                }
                default:{
                    throw new RuntimeException("need a value of postType : feed / reels / story");
                }
            }


            String uri = pageId +"/media";
            Map<String, Object> result = metaFeignClient.callPost(uri ,postData);

            return result;

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Map<String, Object> uploadContainer(String pageId, Map<String, Object> data) {
        return null;
    }
}
