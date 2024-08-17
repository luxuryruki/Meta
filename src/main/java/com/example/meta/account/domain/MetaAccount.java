package com.example.meta.account.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class MetaAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String token; // long term token
    String pageToken; // page long term token
    String pageId;
    String instagramId;

    public void setName(String name) {
        this.name = name;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public void setPageToken(String pageToken) {
        this.pageToken = pageToken;
    }

    public void setInstagramId(String instagramId) {
        this.instagramId = instagramId;
    }
}
