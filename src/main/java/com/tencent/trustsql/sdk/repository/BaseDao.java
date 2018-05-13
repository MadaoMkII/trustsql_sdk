package com.tencent.trustsql.sdk.repository;

import com.tencent.trustsql.sdk.module.beans.BaseRequestModel;

public interface BaseDao {

    void submitRequest(final BaseRequestModel baseRequestModel);

}
