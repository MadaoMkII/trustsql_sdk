package com.tencent.trustsql.sdk;

import com.tencent.trustsql.sdk.command.EncryptDES3Command;
import com.tencent.trustsql.sdk.config.EnvironmentConfig;
import com.tencent.trustsql.sdk.module.beans.RegisterUserModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);


        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();


        EnvironmentConfig environmentConfig=  ctx.getBean(EnvironmentConfig.class);


        System.out.println(environmentConfig.getTrustSqlRequestUrls().get("registerUser"));
    }

}