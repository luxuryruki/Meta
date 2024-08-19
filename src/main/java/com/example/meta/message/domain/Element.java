package com.example.meta.message.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Element {
    String title;
    String subtitle;
    String image_url;
    List<Button> buttons;

    public Element(String title, String subtitle, String image_url, List<Button> buttons) {
        this.title = title;
        this.subtitle = subtitle;
        this.image_url = image_url;
        this.buttons = buttons;
    }
}
