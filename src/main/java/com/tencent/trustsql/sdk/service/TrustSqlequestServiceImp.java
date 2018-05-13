package com.tencent.trustsql.sdk.service;


import com.tencent.trustsql.sdk.config.EnvironmentConfig;
import com.tencent.trustsql.sdk.module.beans.RegisterUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrustSqlequestServiceImp {

    @Autowired
    private EnvironmentConfig environmentConfig;

    public void registerUser(final RegisterUserModel registerUserModel) {

        registerUserModel.assembleReq_data(environmentConfig.getPubKey(), registerUserModel.getUser_fullName(),
                                           registerUserModel.getUser_id());

        RegisterUserModel registerUserModelforRequest = registerUserModel;


    }
}
