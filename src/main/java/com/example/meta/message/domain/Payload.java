package com.example.meta.message.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Payload {
    String template_type;
    List<Element> elements;

    public Payload(String template_type, List<Element> elements) {
        this.template_type = template_type;
        this.elements = elements;
    }
}
