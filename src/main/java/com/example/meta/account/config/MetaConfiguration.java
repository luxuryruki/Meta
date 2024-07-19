package com.example.meta.account.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class MetaConfiguration {

    @Value("${meta.facebook.graph-url-prefix}")
    private String graphUrlPrefix;

    @Value("${meta.facebook.client-id}")
    private String clientId;

    @Value("${meta.facebook.client-secret}")
    private String clientSecret;
}
