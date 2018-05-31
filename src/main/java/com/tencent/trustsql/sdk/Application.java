package com.tencent.trustsql.sdk;


import com.fasterxml.jackson.databind.ObjectMapper;
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
        ObjectMapper mapper = new ObjectMapper();

        TrustSqlRequestServiceImp serviceImp = ctx.getBean(TrustSqlRequestServiceImp.class);
        //        IssAppendModel issAppendModel = IssAppendModel.builder().accountPriKey("12435").chain_id
        // ("ch_tencent_test").
        //                content("{\"name\":\"ding\"}").info_version("1").notes("{\"Tieile\":\"ding\"}")
        //                .ledger_id("ld_tencent_dam").node_id("nd_tencent_test4").sign(null).state("5").version
        // ("1.0").build();


        //        IssueCashApplyModel issueCashApplyModel = IssueCashApplyModel.builder().amount(69825).asset_type("5")
        //                .chain_id("ch_tencent_test").ledger_id("ld_tencent_dam").node_id("nd_tencent_test4").unit
        // ("Li").
        //                        owner_account("19jCbh8TMVTShGnrzVKLdD8fMpo5bry4pE").content("{\"test\":\"test\"}")
        // .build();

        RegisterUserAccountModel registerUserAccountModel = RegisterUserAccountModel.builder().user_id("Aptx0086")
                .product_code("UC0079").pubKey("A1UHp2NQpfduFV4TW3rFFNLkCB4UkLZj0HvRH+VErzR6").build();

        serviceImp.doRequest(registerUserAccountModel);

        //System.out.println(new IssueApplyCommand().execute());

    }

}