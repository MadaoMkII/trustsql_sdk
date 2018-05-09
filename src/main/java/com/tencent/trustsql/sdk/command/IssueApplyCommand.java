package com.tencent.trustsql.sdk.command;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tencent.trustsql.sdk.config.Constants;
import com.tencent.trustsql.sdk.config.TrustSDK;
import com.tencent.trustsql.sdk.util.HttpClientUtil;
import com.tencent.trustsql.sdk.util.SignStrUtil;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class IssueApplyCommand implements Command {

    @Override
    public String execute(String... args) throws Exception {

        String mchId = "gbdc8fceaa95d229e";
        String tradePri = "";
        String prvKey = "tLjF8pkr36WBHanLGGTQRRTSQHZCzTxemH1MhwgCiDU=";
        // String prvKey = args[2];
        // {\"mchId\":\"gba363879924b43db\",\"prvKey\":\"xhQ+g2qvtW+Y6oRd2+UHkQ9J5S7pZvqc9lw0l1xr0Is=\",
        // \"version\":\"1.0\",\"node_id\":\"nded6c6ea234da385\",\"chain_id\":\"ched6c6ea234da385\",
        // \"ledger_id\":\"lded6c6ea234da385\",\"info_key\":\"info_key_test1\",\"info_version\":\"2\",\"state\":\"0\",
        // \"content\":{\"test\":\"test\"},\"notes\":{\"test\":\"test\"},\"commit_time\":\"2018-01-0100:00:00\",
        // \"account\":\"1Pk1EpQQTVAaLDQGvYbtR1AN6ySdmwZgw2\",
        // \"public_key\":\"Auuo4reS99kRPtyiyRM7sFZ0rPWwDb973HasSddwZZvp\"}
        //String jsonArr = args[3];
        JSONArray resultJsonArr = new JSONArray();
        //System.out.println("jsonArr: " + jsonArr);
        //JSONObject jsonObj = JSONObject.parseObject(jsonArr);
        Map<String, Object> paramMap = new TreeMap<String, Object>();
        paramMap.put("version", "1.0");
        paramMap.put("sign_type", "ECDSA");
        paramMap.put("mch_id", mchId);
        paramMap.put("node_id", "nd_tencent_test4");
        paramMap.put("chain_id", "ch_tencent_test");
        paramMap.put("ledger_id", "ld_tencent_dam");
        paramMap.put("amount", "8908");
        paramMap.put("unit", "la");
        paramMap.put("asset_type", "5");
        paramMap.put("owner_account", "19jCbh8TMVTShGnrzVKLdD8fMpo5bry4pE");
        paramMap.put("content", JSONObject.parse("{\"test\":\"test\"}"));
        paramMap.put("timestamp", System.currentTimeMillis() / 1000);
        paramMap.put("mch_sign", TrustSDK.signString(prvKey, SignStrUtil.mapToKeyValueStr(paramMap).getBytes(), false));


        JSONObject postJson = new JSONObject();
        for (String key : paramMap.keySet()) {
            postJson.put(key, paramMap.get(key));
        }

        String url = "https://baas.trustsql.qq.com/cgi-bin/v1.0/dam_asset_issue_apply_v1.cgi";
        System.out.println("Append information into TrustSQL:" + postJson.toJSONString());
        String result = HttpClientUtil.post(url, postJson.toJSONString());
        System.out.println("Append information into TrustSQL:" + postJson.toJSONString());
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
        resultJsonArr.add(0, resultJson);
        return resultJsonArr.toJSONString();
    }

}
