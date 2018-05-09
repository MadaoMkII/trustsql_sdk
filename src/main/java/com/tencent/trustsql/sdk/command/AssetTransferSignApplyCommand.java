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

public class AssetTransferSignApplyCommand implements Command {

    @Override
    public String execute(String... args) throws Exception {

        String mchId = args[1];
        String prvKey = args[2];
        String jsonArr = args[3];
        JSONArray resultJsonArr = new JSONArray();
        System.out.println("jsonArr: " + jsonArr);
        JSONObject jsonObj = JSONObject.parseObject(jsonArr);
        Map<String, Object> paramMap = new TreeMap<String, Object>();
        paramMap.put("version", jsonObj.getString("version"));
        paramMap.put("sign_type", "ECDSA");
        paramMap.put("mch_id", mchId);
        paramMap.put("node_id", jsonObj.getString("node_id"));

        paramMap.put("chain_id", jsonObj.getString("chain_id"));
        paramMap.put("ledger_id", jsonObj.getString("ledger_id"));
        paramMap.put("src_account", jsonObj.getString("src_account"));
        paramMap.put("dst_account", jsonObj.getString("dst_account"));
        paramMap.put("fee_account", jsonObj.getString("fee_account"));
        paramMap.put("mid_asset_id", "26aDURpYZV4svZZGiSVGvT1HNAmuv8VnnVDuudSZ9Gvn3zP");
        paramMap.put("asset_type", jsonObj.getString("asset_type"));
        paramMap.put("amount", jsonObj.getString("amount"));
        paramMap.put("fee_amount", jsonObj.getString("fee_amount"));
        paramMap.put("op_code", jsonObj.getString("op_code"));
        paramMap.put("extra_info", jsonObj.getJSONObject("extra_info"));
        paramMap.put("timestamp", System.currentTimeMillis() / 1000);
        paramMap.put("mch_sign", TrustSDK.signString(prvKey, SignStrUtil.mapToKeyValueStr(paramMap).getBytes(), false));
        // generate post data
        JSONObject postJson = new JSONObject();
        for (String key : paramMap.keySet()) {
            postJson.put(key, paramMap.get(key));
        }
        String url = "https://baas.trustsql.qq.com/cgi-bin/v1.0/dam_asset_signin_apply_v1.cgi";
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
        resultJsonArr.add(0, resultJson);
        return resultJsonArr.toJSONString();
    }

}
