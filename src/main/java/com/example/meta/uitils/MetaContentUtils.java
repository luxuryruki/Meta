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

    public Map<String, Object> postSingleContent(String igId, Map<String, Object> data) {
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


            String uri = igId +"/media";
            Map<String, Object> result = metaFeignClient.callPost(uri ,postData);

            return result;

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Map<String, Object> uploadContainer(String igId, String containerId, Map<String, Object> data) {

        Map<String,Object> response = new HashMap<>();
        try {
            Map<String, Object> uploadData = new HashMap<>();
            uploadData.put("creation_id",containerId);
            uploadData.put("access_token", data.get("access_token"));

            String uri = igId +"/media_publish";
            Map<String, Object> result = metaFeignClient.callPost(uri ,uploadData);
            return result;
//            String apiUrl = configuration.getGraphUrlPrefix() + "/"+ node.getStringValue("instagramId") + "/media_publish";
//            ApiUtils.callApiMethod(apiUrl, null, uploadData, 5000, 10000, HttpMethod.POST, null);
//            logger.info("게시물 업로드 성공");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public boolean isAvailableToUpload(String containerId, Map<String, Object> data){
        int retry = 0;
        int max_retry = 10;
        for(int i = 0; i <= max_retry+1; i++){
            String status = checkContainerStatus(containerId,data);
            if(status.equals("FINISHED")){
                return true;
            }else if(status.equals("IN_PROGRESS")){
                if(max_retry >= retry){
                    retry++;
                    System.out.println(retry); // count try
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        throw new RuntimeException(e);
                    }
                }else{
                    break;
                }
            }else {
                throw new RuntimeException("Not allowed to upload. :"  + status);

            }
        }
        return false;
    }

    private String checkContainerStatus(String containerId, Map<String, Object> data){
        String apiUrl = configuration.getGraphUrlPrefix() + "/"+ containerId;

        Map<String, Object> containerData = new HashMap<>();
        containerData.put("fields","status_code");

        containerData.put("access_token", data.get("access_token"));

        String uri = containerId;
        Map<String, Object> result = metaFeignClient.callGet(uri ,containerData);
        return (String) result.get("status_code");
    }
}
