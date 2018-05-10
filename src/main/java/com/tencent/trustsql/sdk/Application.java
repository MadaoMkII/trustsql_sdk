package com.tencent.trustsql.sdk;


import com.tencent.trustsql.sdk.module.beans.UserRequestModel;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception {
//        ApplicationContext ctx = SpringApplication.run(Application.class, args);
//
//
//        System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//        String[] beanNames = ctx.getBeanDefinitionNames();
//
//
//        String name = ctx.getBean("commonConfig").getClass().toString();
//        AssetTransferApplyCommand asset = (AssetTransferApplyCommand) ctx.getBean("assetTransferApplyCommand");

        UserRequestModel vv = new UserRequestModel();

        vv.setSign("!!");
        vv.validate();
        System.out.println(vv.toJsonNode());

    }

}