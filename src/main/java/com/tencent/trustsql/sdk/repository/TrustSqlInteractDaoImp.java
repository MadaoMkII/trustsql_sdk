package com.tencent.trustsql.sdk.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tencent.trustsql.sdk.config.Constants;
import com.tencent.trustsql.sdk.config.EnvironmentConfig;
import com.tencent.trustsql.sdk.config.TrustSDK;
import com.tencent.trustsql.sdk.module.beans.BaseRequestModel;
import com.tencent.trustsql.sdk.util.HttpClientUtil;
import com.tencent.trustsql.sdk.util.SignStrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Map;

@Repository
public class TrustSqlInteractDaoImp implements BaseDao {

    @Autowired
    private EnvironmentConfig environmentConfig;

    @Override//TODO e也许需要在验证
    public void submitRequest(final BaseRequestModel baseRequestModel) throws IOException {
        String requestUrlParam = baseRequestModel.getClass().getSimpleName().
                replace("Model", "").toLowerCase();

        String url = environmentConfig.getTrustSqlRequestUrls().get(requestUrlParam);
        System.out.println(baseRequestModel.toJsonNode());

        //String result = HttpClientUtil.post(url, baseRequestModel.toJsonNode().toString());
        //System.out.println(result);





    }
}
