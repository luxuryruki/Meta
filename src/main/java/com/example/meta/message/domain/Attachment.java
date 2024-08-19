package com.example.meta.message.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Attachment {
    String type;
    Payload payload;

    public Attachment(String type, Payload payload) {
        this.type = type;
        this.payload = payload;
    }
}
