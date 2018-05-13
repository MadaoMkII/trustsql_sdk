package com.tencent.trustsql.sdk.command;


import com.alibaba.fastjson.JSONObject;

import com.tencent.trustsql.sdk.config.EnvironmentConfig;
import com.tencent.trustsql.sdk.config.TrustSDK;
import com.tencent.trustsql.sdk.util.HttpClientUtil;
import com.tencent.trustsql.sdk.util.SignStrUtil;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.TreeMap;
@Getter
@Component
public class AssetTransferApplyCommand implements Command {

    @Autowired
    private EnvironmentConfig commonConfig;

    @Override
    public String execute(String... args) throws Exception {

        //System.out.println(trustSqlConfig.getRestAPIUrl());


        Map<String, Object> paramMap = new TreeMap<>();
        paramMap.put("version", "1.0");
        paramMap.put("sign_type", "ECDSA");
       // paramMap.put("mch_id", commonConfig.t());
        //paramMap.put("node_id", "nd_tencent_test4");
        paramMap.put("chain_id", "ch_tencent_test");
        paramMap.put("ledger_id", "ld_tencent_dam");
        paramMap.put("src_account", "19jCbh8TMVTShGnrzVKLdD8fMpo5bry4pE");
        paramMap.put("dst_account", "19A1PucYYuQ74N4Ly13PyDM885umzthNub");
        //paramMap.put("fee_account", "19A1PucYYuQ74N4Ly13PyDM885umzthNub");
        paramMap.put("src_asset_id", "26aJNnrmeYyhTphodzQgFuxo9obxpJPgW2AqEeqUijLhn9H");
        paramMap.put("mid_asset_id", "26aJNnrmeYyhTphodzQgFuxo9obxpJPgW2AqEeqUijLhn9H");
        paramMap.put("asset_type", "5");
        paramMap.put("amount", "72");
        //paramMap.put("fee_amount", "4");
        paramMap.put("sign_in_date", "2018-05-06 12:01:11");
        paramMap.put("op_code", "2");
        paramMap.put("extra_info", JSONObject.parse("{\"type\":\"001\"}"));
        paramMap.put("timestamp", System.currentTimeMillis() / 1000);
        String prvKey = "tLjF8pkr36WBHanLGGTQRRTSQHZCzTxemH1MhwgCiDU=";
        paramMap.put("mch_sign", TrustSDK.signString(prvKey, SignStrUtil.mapToKeyValueStr(paramMap).getBytes(), false));
        // generate post data
        JSONObject postJson = new JSONObject();
        for (String key : paramMap.keySet()) {
            postJson.put(key, paramMap.get(key));
        }
        String url = "https://baas.trustsql.qq.com/cgi-bin/v1.0/dam_asset_transfer_mid_apply_v1.cgi";
        System.out.println("Ap11pend information into TrustSQL:" + postJson.toJSONString());
        String result = HttpClientUtil.post(url, postJson.toJSONString());
        // 分析http请求结果
        JSONObject resultJson = JSONObject.parseObject(result);


        return result;
    }

}
