package com.tencent.trustsql.sdk.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tencent.trustsql.sdk.config.EnvironmentConfig;
import com.tencent.trustsql.sdk.config.TrustSDK;
import com.tencent.trustsql.sdk.module.beans.BaseRequestModel;
import com.tencent.trustsql.sdk.module.beans.RegisterUserAccountModel;
import com.tencent.trustsql.sdk.repository.BaseDao;
import com.tencent.trustsql.sdk.util.SignStrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TrustSqlRequestServiceImp {

    @Autowired
    private EnvironmentConfig environmentConfig;
    @Autowired
    private BaseDao baseDao;

    public void registerUser(final BaseRequestModel baseRequestModel) throws Exception {

        baseRequestModel.finalizeModel(environmentConfig);

        baseDao.submitRequest(baseRequestModel);
    }

}
