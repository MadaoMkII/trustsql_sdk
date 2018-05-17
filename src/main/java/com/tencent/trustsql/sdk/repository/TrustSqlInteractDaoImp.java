package com.tencent.trustsql.sdk.repository;

import com.tencent.trustsql.sdk.config.EnvironmentConfig;
import com.tencent.trustsql.sdk.module.beans.BaseRequestModel;
import com.tencent.trustsql.sdk.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TrustSqlInteractDaoImp implements BaseDao {

    @Autowired
    private EnvironmentConfig environmentConfig;

    @Override
    public void submitRequest(final BaseRequestModel baseRequestModel) {
        String requestUrlParam = baseRequestModel.getClass().getSimpleName().
                replace("Model", "").toLowerCase();

        String url = environmentConfig.getTrustSqlRequestUrls().get(requestUrlParam);
        System.out.println(baseRequestModel.toJsonNode());
        String result = HttpClientUtil.post(url, baseRequestModel.toJsonNode().toString());
        System.out.println(result);


    }
}
