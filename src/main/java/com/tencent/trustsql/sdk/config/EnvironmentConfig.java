package com.tencent.trustsql.sdk.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "baas")
@Component
@Setter
@Getter
public class EnvironmentConfig {

    private final Map<String, String> trustSqlRequestUrls = new HashMap<>();

    private String url;

    private String priKey;
    private String mch_id;
    private String pubKey;

}