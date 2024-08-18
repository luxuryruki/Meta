package com.example.meta.message.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageRequest {
    Recipient recipient;
    Message message;

    public MessageRequest(Recipient recipient, Message message) {
        this.recipient = recipient;
        this.message = message;
    }
}
