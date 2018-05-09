package com.tencent.trustsql.sdk;


import com.tencent.trustsql.sdk.command.AssetTransferApplyCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);


        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();


        String name = ctx.getBean("commonConfig").getClass().toString();
        AssetTransferApplyCommand asset = (AssetTransferApplyCommand) ctx.getBean("assetTransferApplyCommand");



    }

}