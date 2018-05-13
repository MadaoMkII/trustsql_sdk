package com.tencent.trustsql.sdk.repository;

import com.alibaba.fastjson.JSONObject;
import com.tencent.trustsql.sdk.config.EnvironmentConfig;
import com.tencent.trustsql.sdk.module.beans.BaseRequestModel;
import com.tencent.trustsql.sdk.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class TrustSqlInteractDao implements BaseDao {

    @Autowired
    private EnvironmentConfig environmentConfig;

    @Override
    public void submitRequest(final BaseRequestModel baseRequestModel) {
        String url = "https://baas.trustsql.qq.com/idm_v1.1/api/user_cert/register";

        String result = HttpClientUtil.post(environmentConfig.getUrl(), baseRequestModel.toJsonNode().toString());
        System.out.println(result);



    }
}
