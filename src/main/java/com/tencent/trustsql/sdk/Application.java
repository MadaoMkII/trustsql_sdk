package com.tencent.trustsql.sdk;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tencent.trustsql.sdk.module.beans.IssAppendModel;
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
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree("{\"name\":\"ding\"}");

        TrustSqlRequestServiceImp environmentConfig = ctx.getBean(TrustSqlRequestServiceImp.class);
        IssAppendModel issAppendModel = IssAppendModel.builder().accountPriKey("12435").chain_id("ch_tencent_test").
                content("{\"name\":\"ding\"}").info_version("1").notes(actualObj)
                .ledger_id("ld_tencent_dam").node_id("nd_tencent_test4").sign(null).state("5").version("1.0").build();

        System.out.println(issAppendModel.getContent());
         environmentConfig.registerUser(issAppendModel);


    }

}