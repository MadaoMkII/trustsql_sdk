package com.tencent.trustsql.sdk.command;

import com.alibaba.fastjson.JSONObject;
import com.tencent.trustsql.sdk.config.TrustSDK;
import com.tencent.trustsql.sdk.util.HttpClientUtil;
import com.tencent.trustsql.sdk.util.SignStrUtil;

import java.util.Map;
import java.util.TreeMap;

public class QueryIssueCommand implements Command {


    @Override
    public String execute(String... args) throws Exception {

        Map<String, Object> paramMap = new TreeMap<>();
        paramMap.put("version", "1.0");
        paramMap.put("sign_type", "ECDSA");
        paramMap.put("mch_id", "gbdc8fceaa95d229e");
        paramMap.put("node_id", "nd_tencent_test4");
        paramMap.put("chain_id", "ch_tencent_test");
        paramMap.put("ledger_id", "ld_tencent_dam");
        paramMap.put("asset_account", "19A1PucYYuQ74N4Ly13PyDM885umzthNub");
        paramMap.put("state", "0");
        paramMap.put("page_limit", "20");
        paramMap.put("page_no", "1");
        paramMap.put("timestamp", System.currentTimeMillis() / 1000);
        String prvKey = "tLjF8pkr36WBHanLGGTQRRTSQHZCzTxemH1MhwgCiDU=";
        paramMap.put("sign", TrustSDK.signString(prvKey,
                                                     SignStrUtil.mapToKeyValueStr(paramMap).getBytes(), false));

        // generate post data
        JSONObject postJson = new JSONObject();
        for (String key : paramMap.keySet()) {
            postJson.put(key, paramMap.get(key));
        }
        String url = "https://baas.trustsql.qq.com/cgi-bin/v1.0/dam_asset_account_query_v1.cgi";
        System.out.println("Ap11pend information into TrustSQL:" + postJson.toJSONString());
        String result = HttpClientUtil.post(url, postJson.toJSONString());
        // 分析http请求结果
        JSONObject resultJson = JSONObject.parseObject(result);
        boolean mchSignValid = TrustSDK.verify(
                "BLCxj+M3xVA5u7IBzzCwLOXzlV4ewZiiZ62lObeK0YV3b1j3PTfQT6Y3Aa60GuhUfgWibAxuRDiFrWgJqJW10QQ=",
                SignStrUtil.mapToKeyValueStr(paramMap).getBytes(), false, paramMap.get("mch_sign").toString());
        System.out.println(mchSignValid);

        return result;
    }
}
