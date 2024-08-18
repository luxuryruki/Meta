package com.example.meta.message.domain;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Recipient {
    Long id;

    public Recipient(Long id) {
        this.id = id;
    }
}
