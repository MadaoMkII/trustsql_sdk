package com.tencent.trustsql.sdk.command;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tencent.trustsql.sdk.module.beans.RegisterUserModel;

public class TrustSqlCommand {

    public String execute() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        RegisterUserModel userRequestModel = RegisterUserModel.builder().seq_no("ancde").build();

        JsonNode node = mapper.convertValue(userRequestModel, JsonNode.class);

        String url = "https://baas.trustsql.qq.com/cgi-bin/v1.0/trustsql_iss_append_v1.cgi";

        return null;
    }

}
