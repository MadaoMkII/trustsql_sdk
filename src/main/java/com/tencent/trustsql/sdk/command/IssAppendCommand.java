package com.tencent.trustsql.sdk.command;

import com.alibaba.fastjson.JSONObject;
import com.tencent.trustsql.sdk.config.Constants;
import com.tencent.trustsql.sdk.config.TrustSDK;
import com.tencent.trustsql.sdk.util.HttpClientUtil;
import com.tencent.trustsql.sdk.util.SignStrUtil;
import org.apache.commons.codec.binary.Hex;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class IssAppendCommand implements Command {

    @Override
    public String execute(String... args) throws Exception {
        String prvKey = "tLjF8pkr36WBHanLGGTQRRTSQHZCzTxemH1MhwgCiDU=";
        Map<String, Object> paramMap = new TreeMap<>();
        paramMap.put("version", "1.0");
        paramMap.put("sign_type", "ECDSA");
        paramMap.put("mch_id", "gbdc8fceaa95d229e");
        paramMap.put("node_id", "nd_tencent_test4");
        paramMap.put("chain_id", "ch_tencent_test");
        paramMap.put("ledger_id", "ld_tencent_dam");

        paramMap.put("info_key", System.currentTimeMillis() / 1000);
        paramMap.put("info_version", 1);
        paramMap.put("state", 5);
        paramMap.put("content", JSONObject.parse("{\"name\":\"ding \"}"));
        paramMap.put("notes", JSONObject.parse("{\"title\":\"dingWang \"}"));
        // paramMap.put("commit_time", jsonObj.getString("commit_time"));
        paramMap.put("commit_time", "2018-03-03 11:16:23");
        paramMap.put("account", TrustSDK.generateAddrByPrvkey(prvKey));
        paramMap.put("public_key", TrustSDK.generatePubkeyByPrvkey(prvKey, true));
        // 测试共享信息申请替换成实际接口中返回的sign_src:fb983cd7ee06c4d81851800294f24b7978420d131be88934908d0a08a7fea9c7
        if (paramMap.containsKey("sign")) {
            paramMap.put("sign",
                         TrustSDK.SignRenString(prvKey, Hex.decodeHex(paramMap.get("sign").toString().toCharArray())));
        }
        paramMap.put("mch_sign", TrustSDK.signString(prvKey, SignStrUtil.mapToKeyValueStr(paramMap).getBytes(), false));
        // generate post data
        JSONObject postJson = new JSONObject();
        for (String key : paramMap.keySet()) {
            postJson.put(key, paramMap.get(key));
        }
        String url = "https://baas.trustsql.qq.com/cgi-bin/v1.0/trustsql_iss_append_v1.cgi";
        System.out.println("Append information into TrustSQL:" + postJson.toJSONString());
        String result = HttpClientUtil.post(url, postJson.toJSONString());
        // 分析http请求结果
        JSONObject resultJson = JSONObject.parseObject(result);
        if ("0".equals(resultJson.getString("retcode")) && "OK".equals(resultJson.getString("retmsg"))) {
            // 验证返回数据的mch_sign
            paramMap.clear();
            for (Entry<String, Object> entry : resultJson.entrySet()) {
                if (!"mch_sign".equals(entry.getKey())) {
                    paramMap.put(entry.getKey(), entry.getValue());
                }
            }
            boolean mchSignValid = TrustSDK.verifyString(Constants.INFO_SHARE_PUBKEY,
                                                         SignStrUtil.mapToKeyValueStr(paramMap),
                                                         resultJson.getString("mch_sign"));
            resultJson.put("mch_sign_verify", mchSignValid);
        }
        //resultJsonArr.add(0, resultJson);
        return result.toString();
    }

}
