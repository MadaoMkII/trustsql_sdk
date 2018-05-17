package com.tencent.trustsql.sdk;


import com.tencent.trustsql.sdk.module.beans.RegisterUserAccountModel;

import com.tencent.trustsql.sdk.service.TrustSqlRequestServiceImp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);


        System.out.println("Let's inspect the beans provided by Spring Boot:");

        // String[] beanNames = ctx.getBeanDefinitionNames();


        TrustSqlRequestServiceImp environmentConfig = ctx.getBean(TrustSqlRequestServiceImp.class);

        environmentConfig.registerUser(
                RegisterUserAccountModel.builder().product_code("UC0079").user_id("RX78NT1").pubKey(
                        "BAc72jJ+ZPXDVWt2ksz4vh+2bySDBfJGz+ByRQtrrE7nu/LUjWcOKTDT4cmZ8rW+NllhglNzMA7zSujT1FJxotg=").build());


    }

}