package com.tencent.trustsql.sdk.repository;

import com.tencent.trustsql.sdk.module.beans.BaseRequestModel;

import java.io.IOException;
import java.util.Map;

public interface BaseDao {

    void submitRequest(final Map<String, Object> requestResult) throws Exception;

}
