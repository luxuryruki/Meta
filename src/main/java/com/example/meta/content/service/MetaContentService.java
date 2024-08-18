package com.example.meta.content.service;

import com.example.meta.feign.MetaFeignClient;
import com.example.meta.configuration.MetaConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MetaContentService {

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

    public Map<String, Object> postSingleContainer(String igId, Map<String, Object> postData) {
        try {
            String postType = (String) postData.get("postType");
            if(postType != null){
                postData = getSinglePostObjectMap(postType, postData);
            }

            String uri = igId +"/media";
            Map<String, Object> result = metaFeignClient.callPostWithPrams(uri ,postData);

            return result;

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    private static Map<String, Object> getSinglePostObjectMap(String postType, Map<String, Object> data) {
        String url =  (String) data.get("url");
        String caption =  (String) data.get("caption");
        String accessToken =  (String) data.get("access_token");

        Map<String,Object> postData = new HashMap<>();
        postData.put("caption", caption);
        postData.put("access_token", accessToken);
        switch (postType){
            case "feed":{
                if(url.toLowerCase().endsWith(".jpeg") || url.toLowerCase().endsWith(".jpg")){
                    postData.put("image_url", url);
                }else {
                    throw new RuntimeException("not allowed to upload the file as feed");
                }

                break;
            }
            case "reels":{
                if(url.toLowerCase().endsWith(".mov") || url.toLowerCase().endsWith(".mp4") || url.toLowerCase().endsWith(".avi")){
                    postData.put("video_url", url);
                    postData.put("media_type","REELS");
                }else {
                    throw new RuntimeException("not allowed to upload the file as reels");
                }

                break;
            }
            case "story":{
                postData.put("media_type","STORIES");

                if(url.toLowerCase().endsWith(".mov") || url.toLowerCase().endsWith(".mp4") || url.toLowerCase().endsWith(".avi")){
                    postData.put("video_url", url);
                }else if(url.toLowerCase().endsWith(".jpeg") || url.toLowerCase().endsWith(".jpg")){
                    postData.put("image_url", url);
                }else {
                    throw new RuntimeException("not allowed to upload the file : ");
                }

                break;
            }
            default:{
                throw new RuntimeException("need a value of postType : feed / reels / story");
            }
        }
        return postData;
    }

    public Map<String, Object> postSlideContainer(String igId, Map<String, Object> data) {
        String uri = igId +"/media";
        Map<String, Object> result = metaFeignClient.callPostWithPrams(uri ,data);
        return result;
    }

    public Map<String, Object> uploadContainer(String igId, String containerId, Map<String, Object> data) {

        Map<String,Object> response = new HashMap<>();
        try {
            Map<String, Object> uploadData = new HashMap<>();
            uploadData.put("creation_id",containerId);
            uploadData.put("access_token", data.get("access_token"));

            String uri = igId +"/media_publish";
            Map<String, Object> result = metaFeignClient.callPostWithPrams(uri ,uploadData);
            return result;
//            String apiUrl = configuration.getGraphUrlPrefix() + "/"+ node.getStringValue("instagramId") + "/media_publish";
//            ApiUtils.callApiMethod(apiUrl, null, uploadData, 5000, 10000, HttpMethod.POST, null);
//            logger.info("게시물 업로드 성공");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public List<String> getAvailableContainer(Queue<String> queue, String token){
        int retry = 0;
        int max_retry = 60;
        List<String> children = new ArrayList<>();
        while (!queue.isEmpty()){
            String currentId = queue.poll();
            String status = checkContainerStatus(currentId, token);
            if(status.equals("FINISHED")){
                children.add(currentId);
            }else if(status.equals("IN_PROGRESS")){
                if(max_retry >= retry){
                    queue.add(currentId);
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        throw new RuntimeException(e);
                    }
                    retry++;
                    System.out.println("getAvailableContainer : " + retry);
                }else{
                    throw new RuntimeException();
                }
            }else{
                throw new RuntimeException();
            }
        }
        return children;
    }

    public boolean isAvailableToUpload(String containerId, Map<String, Object> data){
        int retry = 0;
        int max_retry = 60;
        for(int i = 0; i <= max_retry+1; i++){
            String status = checkContainerStatus(containerId,(String) data.get("access_token"));
            if(status.equals("FINISHED")){
                return true;
            }else if(status.equals("IN_PROGRESS")){
                if(max_retry >= retry){
                    retry++;
                    System.out.println("isAvailableToUpload : " + retry); // count try
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

    private String checkContainerStatus(String containerId, String token){
        String apiUrl = configuration.getGraphUrlPrefix() + "/"+ containerId;

        Map<String, Object> containerData = new HashMap<>();
        containerData.put("fields","status_code");

        containerData.put("access_token", token);

        String uri = containerId;
        Map<String, Object> result = metaFeignClient.callGet(uri ,containerData);
        return (String) result.get("status_code");
    }
}
