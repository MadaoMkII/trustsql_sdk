package com.tencent.trustsql.sdk.service;


import com.tencent.trustsql.sdk.config.EnvironmentConfig;
import com.tencent.trustsql.sdk.module.beans.BaseRequestModel;
import com.tencent.trustsql.sdk.repository.BaseDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class TrustSqlRequestServiceImp {

    @Autowired
    private EnvironmentConfig environmentConfig;
    @Autowired
    private BaseDao baseDao;

    public void doRequest(final BaseRequestModel baseRequestModel) throws Exception {

        Map<String, Object> result = baseRequestModel.finalizeModel(environmentConfig);

        baseDao.submitRequest(result);
    }

}
