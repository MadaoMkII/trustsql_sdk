package com.tencent.trustsql.sdk.command;

import com.tencent.trustsql.sdk.config.TrustSDK;
import com.tencent.trustsql.sdk.util.SignStrUtil;

import java.util.Map;
import java.util.TreeMap;

public interface Command {

    String execute(String... args) throws Exception;

    default Map<String, Object> finalizeParamMap(final String prvkey, final Map<String, Object> unsignedMap) throws
            Exception {
        Map<String, Object> signedMap = new TreeMap<>();
        signedMap.putAll(unsignedMap);
        signedMap.put("timestamp", System.currentTimeMillis() / 1000);
        signedMap.put("mch_sign",
                      TrustSDK.signString(prvkey, SignStrUtil.mapToKeyValueStr(signedMap).getBytes("UTF-8"), false));


        return signedMap;
    }


}
