package com.example.meta.message.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    String text;
    Attachment attachment;

    public Message(String text, Attachment attachment) {
        this.text = text;
        this.attachment = attachment;
    }
}
