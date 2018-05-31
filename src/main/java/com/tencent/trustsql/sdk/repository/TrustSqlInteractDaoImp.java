package com.tencent.trustsql.sdk.repository;


import com.alibaba.fastjson.JSONObject;
import com.tencent.trustsql.sdk.config.EnvironmentConfig;
import com.tencent.trustsql.sdk.config.TrustSDK;
import com.tencent.trustsql.sdk.util.HttpClientUtil;
import com.tencent.trustsql.sdk.util.SignStrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;


@Repository
public class TrustSqlInteractDaoImp implements BaseDao {

    @Autowired
    private EnvironmentConfig environmentConfig;

    @Override//TODO e也许需要在验证
    public void submitRequest(final Map<String, Object> baseRequestResultMap) throws Exception {
        String requestUrlParam = baseRequestResultMap.get("requestUrlParam").toString();
        baseRequestResultMap.remove("requestUrlParam");
        if (baseRequestResultMap.containsKey("type") && baseRequestResultMap.get("type").equals("sign")) {

            baseRequestResultMap.put("sign", TrustSDK.signString(environmentConfig.getPriKey(),
                                                                 SignStrUtil.mapToKeyValueStr(baseRequestResultMap)
                                                                         .getBytes("UTF-8"), false));
        } else {
            baseRequestResultMap.put("mch_sign", TrustSDK.signString(environmentConfig.getPriKey(),
                                                                     SignStrUtil.mapToKeyValueStr(baseRequestResultMap)
                                                                             .getBytes("UTF-8"), false));
        }
        String url = environmentConfig.getTrustSqlRequestUrls().get(requestUrlParam);

        System.out.println(url);

        JSONObject postJson = new JSONObject();
        for (String key : baseRequestResultMap.keySet()) {
            postJson.put(key, baseRequestResultMap.get(key));
        }
        System.out.println(postJson);
        String result = HttpClientUtil.post(url, postJson.toJSONString());
        System.out.println(result);


    }
}
