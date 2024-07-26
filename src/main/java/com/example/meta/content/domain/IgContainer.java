package com.example.meta.content.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IgContainer {
    private String mediaType; // VIDEO, IMAGE
    private String url;


}
