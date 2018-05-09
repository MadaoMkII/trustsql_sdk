package com.tencent.trustsql.sdk.command;

import com.alibaba.fastjson.JSONObject;
import com.tencent.trustsql.sdk.config.TrustSDK;
import com.tencent.trustsql.sdk.util.HttpClientUtil;
import com.tencent.trustsql.sdk.util.SignStrUtil;

import java.util.Map;
import java.util.TreeMap;

public class QueryUserAccount implements Command {
    @Override
    public String execute(String... args) throws Exception {


        String prvKey = "tLjF8pkr36WBHanLGGTQRRTSQHZCzTxemH1MhwgCiDU=";
        String pubKey = "BDi/bpeBVzNksdxG6OfN0/Ujn2sG147iNpJXCuezKXXtEfm8adjvuOmA4z7jqrrMQmzlSogvKZWBvDWDx/rZRrI=";
        Map<String, Object> paramMap = new TreeMap<>();
        paramMap.put("product_code", "UC0079");
        paramMap.put("mch_id", "gbdc8fceaa95d229e");
        paramMap.put("req_data", "{\"public_key\":\"" + pubKey + "\"," + "\"user_id\":\"RX78NT1\"}");
        paramMap.put("seq_no", System.currentTimeMillis() + "0000000000000000000");
        paramMap.put("time_stamp", System.currentTimeMillis() / 1000);
        paramMap.put("type", "sign");
        paramMap.put("sign", TrustSDK.signString(prvKey, SignStrUtil.mapToKeyValueStr(paramMap).getBytes(), false));

        String url = "https://baas.trustsql.qq.com/idm_v1.1/api/account_cert/fetch_list";

        JSONObject postJson = new JSONObject();
        paramMap.forEach(postJson::put);
        System.out.println(postJson);
        String result = HttpClientUtil.post(url, postJson.toJSONString());
        System.out.println(result);

        return result;
    }
}
