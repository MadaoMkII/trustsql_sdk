package com.tencent.trustsql.sdk.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Getter
@PropertySource({"classpath:application.properties"})
@Component
public class CommonConfig implements Config {


    @Value("${mch_id}")
    public String mchId;

}