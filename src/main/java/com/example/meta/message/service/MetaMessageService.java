package com.example.meta.message.service;

import com.example.meta.configuration.MetaConfiguration;
import com.example.meta.feign.MetaFeignClient;
import com.example.meta.message.domain.Message;
import com.example.meta.message.domain.MessageRequest;
import com.example.meta.message.domain.Recipient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MetaMessageService {

    @Autowired
    private MetaConfiguration configuration;
    @Autowired
    private MetaFeignClient metaFeignClient;

    public Map<String, Object> getChatRoom(String pageId, Map<String, Object> data) {
        try {
            String uri = pageId +"/conversations";

            Map<String, Object> result = metaFeignClient.callGet(uri ,data);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, Object> getMessages(String chatRoomId, Map<String, Object> data) {
        try {
            Map<String, Object> result = metaFeignClient.callGet(chatRoomId ,data);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, Object> getMessageDetail(String messageId, Map<String, Object> data) {
        try {

            Map<String, Object> result = metaFeignClient.callGet(messageId ,data);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public Map<String, Object> sendMessage(Map<String, Object> prams, MessageRequest body) {
        try {
            String uri = "me/messages";

            Map<String, Object> result = metaFeignClient.callPostWitParamsAndBody(uri,prams ,body);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public MessageRequest getMessageRequest(Long recipientId, String text){
        Recipient recipient = new Recipient(recipientId);

        Message message = null;
        if(!text.isEmpty()){
            //normal message
            message = new Message(text, null);
        }else {
            //template message
            //todo 템플릿
        }

        MessageRequest messageRequest = new MessageRequest(recipient, message);

        return messageRequest;
    }

}
