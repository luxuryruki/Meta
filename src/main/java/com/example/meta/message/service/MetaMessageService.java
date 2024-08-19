package com.example.meta.message.service;

import com.example.meta.configuration.MetaConfiguration;
import com.example.meta.feign.MetaFeignClient;
import com.example.meta.message.domain.*;
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
        if(text == null || text.isBlank()){
            //template message

            //button
            List<Button> buttons = new ArrayList<>();
            Button button = new Button("web_url","https://www.google.com/", "Go to Google");
            buttons.add(button);

            //element
            String imageUrl = "https://img.freepik.com/free-vector/instagram-icon_1057-2227.jpg?size=338&ext=jpg&ga=GA1.1.2008272138.1723852800&semt=ais_hybrid";
            List<Element> elements = new ArrayList<>();
            Element element = new Element("title","subtitle",imageUrl,buttons);
            elements.add(element);

            //payload
            Payload payload = new Payload("generic",elements);

            Attachment attachment = new Attachment("template",payload);
            message = new Message(null, attachment);
        }else{
            //normal message
            message = new Message(text, null);
        }

        MessageRequest messageRequest = new MessageRequest(recipient, message);

        return messageRequest;
    }

}
