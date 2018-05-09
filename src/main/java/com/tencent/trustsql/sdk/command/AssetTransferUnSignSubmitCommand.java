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

public class AssetTransferUnSignSubmitCommand implements Command {

    @Override
    public String execute(String... args) throws Exception {
        String mchId = "gbdc8fceaa95d229e";
        String prikey = "tLjF8pkr36WBHanLGGTQRRTSQHZCzTxemH1MhwgCiDU=";
        Map<String, Object> paramMap = new TreeMap<>();
        paramMap.put("version", "1.0");
        paramMap.put("sign_type", "ECDSA");
        paramMap.put("mch_id", mchId);
        paramMap.put("node_id", "nd_tencent_test4");
        paramMap.put("chain_id", "ch_tencent_test");
        paramMap.put("ledger_id", "ld_tencent_dam");
        paramMap.put("transaction_id", "201805061460033173");
        paramMap.put("asset_type", "5");
        paramMap.put("timestamp", System.currentTimeMillis() / 1000);
        String sign_str = "4a2b930208459c67ba9d894d26dee39065b0b3f311fc4baa500ea66f1c460f72";


        String after = TrustSDK.SignRenString("IlwvtHOdcDd4fMUD8NYedteFsCqAggwJJJAEOND3q8o=", Hex.decodeHex(sign_str.toCharArray()));
        ;
        String strAfter = "[{\"id\":\"1\"," + "\"sign_str\":\"" + sign_str + "\"," +
                "\"account\":\"19jCbh8TMVTShGnrzVKLdD8fMpo5bry4pE\"," + "sign:\"" + after + "\"}]";


        paramMap.put("sign_list", JSONObject.parse(strAfter));

        String prvKey = "tLjF8pkr36WBHanLGGTQRRTSQHZCzTxemH1MhwgCiDU=";
        paramMap.put("mch_sign", TrustSDK.signString(prvKey, SignStrUtil.mapToKeyValueStr(paramMap).getBytes(), false));

        JSONObject postJson = new JSONObject();
        for (String key : paramMap.keySet()) {
            postJson.put(key, paramMap.get(key));
        }
        String url = "https://baas.trustsql.qq.com/cgi-bin/v1.0/dam_asset_transfer_submit_v1.cgi";
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

        return result;
    }

}
