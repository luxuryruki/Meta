package com.example.meta.message.controller;

import com.example.meta.account.domain.MetaAccount;
import com.example.meta.account.service.MetaAccountService;
import com.example.meta.configuration.MetaConfiguration;
import com.example.meta.content.service.MetaContentService;
import com.example.meta.message.domain.Message;
import com.example.meta.message.domain.MessageRequest;
import com.example.meta.message.domain.Recipient;
import com.example.meta.message.service.MetaMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController // If you don't use @RestController, Spring boot will render view template.
public class MessageController {

    @Autowired
    private MetaConfiguration configuration;

    @Autowired
    private MetaMessageService metaMessageService;

    @Autowired
    private MetaAccountService metaAccountService;

    @GetMapping("/getChatRoom")
    public Map<String,Object> getChatRoom(@RequestParam("id") Long id) {
        Map<String,Object> response = new HashMap<>();
        try {
            MetaAccount metaAccount =  metaAccountService.read(id).orElse(null);
            if (metaAccount == null) {
                response.put("status", "error");
                response.put("message", "MetaAccount not found");
                return response;
            }

            String token = metaAccount.getPageToken();
            String fbPageId = metaAccount.getPageId();

            Map<String,Object> data = new HashMap<>();
            data.put("access_token", token);
            data.put("platform", "instagram");

            Map<String,Object> result = metaMessageService.getChatRoom(fbPageId,data);
            response.put("status", "success");
            response.put("item", result);
        }catch (Exception e){
            response.put("status", "error");
            response.put("message", e.getMessage());
        }
        return response;
    }

    @GetMapping("/getMessages")
    public Map<String,Object> getMessages(@RequestParam("id") Long id,@RequestParam("chatRoomId") String chatRoomId) {
        Map<String,Object> response = new HashMap<>();
        try {
            MetaAccount metaAccount =  metaAccountService.read(id).orElse(null);
            if (metaAccount == null) {
                response.put("status", "error");
                response.put("message", "MetaAccount not found");
                return response;
            }

            String token = metaAccount.getPageToken();
//            String fbPageId = metaAccount.getPageId();

            Map<String,Object> data = new HashMap<>();
            data.put("access_token", token);
            data.put("fields", "messages");

            Map<String,Object> result = metaMessageService.getMessages(chatRoomId,data);
            response.put("status", "success");
            response.put("item", result);
        }catch (Exception e){
            response.put("status", "error");
            response.put("message", e.getMessage());
        }
        return response;
    }

    @GetMapping("/getMessageDetail")
    public Map<String,Object> getMessageDetail(@RequestParam("id") Long id,@RequestParam("messageId") String messageId) {
        Map<String,Object> response = new HashMap<>();
        try {
            MetaAccount metaAccount =  metaAccountService.read(id).orElse(null);
            if (metaAccount == null) {
                response.put("status", "error");
                response.put("message", "MetaAccount not found");
                return response;
            }

            String token = metaAccount.getPageToken();
//            String fbPageId = metaAccount.getPageId();

            Map<String,Object> data = new HashMap<>();
            data.put("access_token", token);
            data.put("fields", "id,created_time,from,to,message");

            Map<String,Object> result = metaMessageService.getMessageDetail(messageId,data);
            response.put("status", "success");
            response.put("item", result);
        }catch (Exception e){
            response.put("status", "error");
            response.put("message", e.getMessage());
        }
        return response;
    }

    @PostMapping("/sendMessage")
    public Map<String,Object> sendMessage(@RequestParam("id") Long id,@RequestParam("recipientId") Long recipientId, @RequestParam("message") String text) {
        Map<String,Object> response = new HashMap<>();
        try {
            MetaAccount metaAccount =  metaAccountService.read(id).orElse(null);
            if (metaAccount == null) {
                response.put("status", "error");
                response.put("message", "MetaAccount not found");
                return response;
            }

            String token = metaAccount.getPageToken();
            MessageRequest messageRequest = metaMessageService.getMessageRequest(recipientId, text);


            Map<String,Object> prams = new HashMap<>();
            prams.put("access_token",token);

            Map<String,Object> result = metaMessageService.sendMessage(prams,messageRequest);
            response.put("status", "success");
            response.put("item", result);
        }catch (Exception e){
            response.put("status", "error");
            response.put("message", e.getMessage());
        }
        return response;
    }

    @PostMapping("/sendTemplateMessage")
    public Map<String,Object> sendMessage(@RequestParam("id") Long id,@RequestParam("recipientId") Long recipientId) {
        Map<String,Object> response = new HashMap<>();
        try {
            MetaAccount metaAccount =  metaAccountService.read(id).orElse(null);
            if (metaAccount == null) {
                response.put("status", "error");
                response.put("message", "MetaAccount not found");
                return response;
            }

            String token = metaAccount.getPageToken();
            MessageRequest messageRequest = metaMessageService.getMessageRequest(recipientId, null);


            Map<String,Object> prams = new HashMap<>();
            prams.put("access_token",token);

            Map<String,Object> result = metaMessageService.sendMessage(prams,messageRequest);
            response.put("status", "success");
            response.put("item", result);
        }catch (Exception e){
            response.put("status", "error");
            response.put("message", e.getMessage());
        }
        return response;
    }
}
