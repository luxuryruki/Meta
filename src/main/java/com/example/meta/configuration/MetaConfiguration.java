package com.example.meta.configuration;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class MetaConfiguration {



    private final String graphUrlPrefix;
    private final String clientId;
    private final String clientSecret;

    public MetaConfiguration() {
        Dotenv dotenv = Dotenv.load();
        this.graphUrlPrefix = dotenv.get("META_FACEBOOK_GRAPH_URL_PREFIX");
        this.clientId = dotenv.get("META_FACEBOOK_CLIENT_ID");
        this.clientSecret = dotenv.get("META_FACEBOOK_CLIENT_SECRET");
    }

}
