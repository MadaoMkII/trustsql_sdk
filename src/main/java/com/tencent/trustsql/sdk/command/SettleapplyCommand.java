package com.tencent.trustsql.sdk.command;

import com.alibaba.fastjson.JSONObject;
import com.tencent.trustsql.sdk.config.Constants;
import com.tencent.trustsql.sdk.config.TrustSDK;
import com.tencent.trustsql.sdk.util.HttpClientUtil;
import com.tencent.trustsql.sdk.util.SignStrUtil;

import java.util.Map;
import java.util.TreeMap;

public class SettleapplyCommand implements Command {


    @Override
    public String execute(String... args) throws Exception {

        Map<String, Object> paramMap = new TreeMap<>();
        paramMap.put("version", "1.0");
        paramMap.put("sign_type", "ECDSA");
        paramMap.put("mch_id", "gbdc8fceaa95d229e");
        paramMap.put("node_id", "nd_tencent_test4");
        paramMap.put("chain_id", "ch_tencent_test");
        paramMap.put("ledger_id", "ld_tencent_dam");
        paramMap.put("owner_account", "19A1PucYYuQ74N4Ly13PyDM885umzthNub");
        paramMap.put("src_asset_list", "[26aPneC6MzBb3VDxu3H3U871krpz9ypZKSWcotvgwSchut8]");
        paramMap.put("asset_type", "5");
        paramMap.put("amount", "150");
        paramMap.put("extra_info", JSONObject.parse("{\"type\":\"001\"}"));
        paramMap.put("timestamp", System.currentTimeMillis() / 1000);
        String prvKey = "tLjF8pkr36WBHanLGGTQRRTSQHZCzTxemH1MhwgCiDU=";
        paramMap.put("mch_sign", TrustSDK.signString(prvKey,
                                                     SignStrUtil.mapToKeyValueStr(paramMap).getBytes(), false));

        // generate post data
        JSONObject postJson = new JSONObject();
        for (String key : paramMap.keySet()) {
            postJson.put(key, paramMap.get(key));
        }
        String url = "https://baas.trustsql.qq.com/cgi-bin/v1.0/dam_asset_settle_apply_v1.cgi";
        System.out.println("Ap11pend information into TrustSQL:" + postJson.toJSONString());
        String result = HttpClientUtil.post(url, postJson.toJSONString());
        // 分析http请求结果
        JSONObject resultJson = JSONObject.parseObject(result);
        if ("0".equals(resultJson.getString("retcode")) && "OK".equals(resultJson.getString("retmsg"))) {
            // 验证返回数据的mch_sign
            paramMap.clear();
            for (Map.Entry<String, Object> entry : resultJson.entrySet()) {
                if (!"mch_sign".equals(entry.getKey())) {
                    paramMap.put(entry.getKey(), entry.getValue());
                }
            }
            boolean mchSignValid = TrustSDK.verifyString(Constants.INFO_SHARE_PUBKEY,
                                                         SignStrUtil.mapToKeyValueStr(paramMap),
                                                         resultJson.getString("mch_sign"));
            resultJson.put("mch_sign_verify", mchSignValid);
        }

        return result;
    }
}
