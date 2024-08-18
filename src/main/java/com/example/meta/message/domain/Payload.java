package com.example.meta.message.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Payload {
    String template_type;
    List<Element> elements;
}
