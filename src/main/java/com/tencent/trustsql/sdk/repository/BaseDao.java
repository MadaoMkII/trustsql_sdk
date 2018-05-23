package com.tencent.trustsql.sdk.repository;

import com.tencent.trustsql.sdk.module.beans.BaseRequestModel;

import java.io.IOException;

public interface BaseDao {

    void submitRequest(final BaseRequestModel baseRequestModel) throws IOException;

}
