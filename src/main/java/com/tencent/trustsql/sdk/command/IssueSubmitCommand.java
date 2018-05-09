package com.tencent.trustsql.sdk.command;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tencent.trustsql.sdk.config.Constants;
import com.tencent.trustsql.sdk.config.TrustSDK;
import com.tencent.trustsql.sdk.algorithm.BaseAlgorithm;
import com.tencent.trustsql.sdk.util.HttpClientUtil;
import com.tencent.trustsql.sdk.util.SignStrUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class IssueSubmitCommand implements Command {

    @Override
    public String execute(String... args) throws Exception {
        //        if (args == null || args.length < 4 || StringUtils.isEmpty(args[1]) || StringUtils.isEmpty(
        //                args[2]) || StringUtils.isEmpty(args[3])) {
        //            throw new TrustSDKException(ErrorNum.INVALID_PARAM_ERROR.getRetCode(),
        //                                        ErrorNum.INVALID_PARAM_ERROR.getRetMsg());
        //        }
        String mchId = "gbdc8fceaa95d229e";
        String tradePri= "";
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
        paramMap.put("asset_type", "5");
        String sign_str = "d41db9524264b96a7ca387035da4b155f706032e4356388b13dbf85de041bf46";

        byte[] hash256 = BaseAlgorithm.encode("SHA-256", "ld_tencent_dam".getBytes("UTF-8"));
        String newPri = Base64.encodeBase64String(hash256);

        String after = TrustSDK.SignRenString(newPri, Hex.decodeHex(
                sign_str.toCharArray()));
        System.err.println(after);
        String strAfter = "[{\"id\":\"1\"," +
                "\"sign_str\":\""+sign_str+"\"," +
                "\"account\":\"1KqApDZZvwtAjxRrpUnT5RXHvzKn1ppL3P\"," + "sign:\"" + after + "\"}]";


        paramMap.put("sign_list", JSONObject.parse(strAfter));
        paramMap.put("transaction_id", "201805051450031620");
        paramMap.put("asset_id", "26aJNnrmeYyhTphodzQgFuxo9obxpJPgW2AqEeqUijLhn9H");
        paramMap.put("timestamp", System.currentTimeMillis() / 1000);

        paramMap.put("mch_sign", TrustSDK.signString(prvKey, SignStrUtil.mapToKeyValueStr(paramMap).getBytes(), false));
        // generate post data
        JSONObject postJson = new JSONObject();
        for (String key : paramMap.keySet()) {
            postJson.put(key, paramMap.get(key));
        }
        String url = "https://baas.trustsql.qq.com/cgi-bin/v1.0/dam_asset_issue_submit_v1.cgi";
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
        System.out.println(resultJson);
        return resultJson.toJSONString();
    }

}
