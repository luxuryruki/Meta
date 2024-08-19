package com.example.meta.message.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Button {
    String type;
    String url;
    String title;

    public Button(String type, String url, String title) {
        this.type = type;
        this.url = url;
        this.title = title;
    }
}
