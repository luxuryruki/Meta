package com.example.meta.content.controller;

import com.example.meta.account.domain.MetaAccount;
import com.example.meta.account.service.MetaAccountService;
import com.example.meta.configuration.MetaConfiguration;
import com.example.meta.content.domain.ContainerRequest;
import com.example.meta.content.domain.IgContainer;
import com.example.meta.content.utils.MetaContentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
            Map<String,Object> container = metaContentUtils.postSingleContainer(igId,data);
            String containerId = (String) container.get("id");

            //Todo check container status logic
            /*
            * if container is not ready, you may get an error
            * */
            if(metaContentUtils.isAvailableToUpload(containerId, data)){
                // upload container
                Map<String,Object> result = metaContentUtils.uploadContainer(igId, containerId, data);
                response.put("status", "success");
                response.put("item", result);
            }else {
                response.put("status", "error");
                response.put("message", "Not allowed to upload. : Time over");
            }

        }catch (Exception e){
            response.put("status", "error");
            response.put("message", e.getMessage());
        }
        return response;
    }

    @PostMapping("/postMultiMedia") // feed only
    public Map<String,Object> postMultiContent(@RequestParam("id") Long id, @RequestParam("caption") String caption, @RequestBody ContainerRequest containerDTO){

        Map<String,Object> response = new HashMap<>();
        MetaAccount metaAccount =  metaAccountService.read(id).orElse(null);
        if (metaAccount == null) {
            response.put("status", "error");
            response.put("message", "MetaAccount not found");
            return response;
        }

        String token = metaAccount.getToken();
        String igId = metaAccount.getInstagramId();
        try {
            List<IgContainer> containers = containerDTO.getContainers();
            Queue<String> containerList = new LinkedList<>(); // Queue (FIFO) actively utilized for sequentially checking the status of containers
            for(IgContainer containerNode : containers){
                String mediaType =containerNode.getMediaType().toUpperCase();

                Map<String,Object> containerData = new HashMap<>();
                containerData.put("access_token", token);
                containerData.put("is_carousel_item", true);
                containerData.put("media_type", mediaType);
                if(mediaType.equals("VIDEO")){
                    containerData.put("video_url", containerNode.getUrl());
                }else{
                    containerData.put("image_url", containerNode.getUrl());
                }
                Map<String,Object> container = metaContentUtils.postSingleContainer(igId,containerData);
                String containerId = (String) container.get("id");
                containerList.add(containerId);


            }

            //Todo status check
            List<String> children = metaContentUtils.getAvailableContainer(containerList,token);
            Map<String,Object> slideData = new HashMap<>();
            slideData.put("access_token",token);
            slideData.put("media_type","CAROUSEL");
            slideData.put("children",String.join(",",children));
            slideData.put("caption",caption);

            //create slide container
            Map<String,Object> container = metaContentUtils.postSlideContainer(igId,slideData);
            String containerId = (String) container.get("id");

            if(metaContentUtils.isAvailableToUpload(containerId, slideData)){
                //upload slide container
                Map<String,Object> result = metaContentUtils.uploadContainer(igId, containerId, slideData);
                response.put("status", "success");
                response.put("item", result);
            }else {
                response.put("status", "error");
                response.put("message", "Not allowed to upload. : Time over");
            }




        }catch (Exception e){
            response.put("status", "error");
            response.put("message", e.getMessage());
        }

        return response;
    }
}
