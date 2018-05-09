package com.tencent.trustsql.sdk.command;

import com.alibaba.fastjson.JSONObject;
import com.tencent.trustsql.sdk.config.TrustSDK;
import com.tencent.trustsql.sdk.algorithm.ECDSAAlgorithm;
import com.tencent.trustsql.sdk.bean.PairKey;

public class GeneratePairKeyCommand implements Command {

	@Override
	public String execute(String... args) throws Exception {
		System.out.println("Generate a pair of public key and private key:");
		PairKey pairKey = TrustSDK.generatePairKey(true);
		return JSONObject.toJSONString(pairKey);
	}

	public static void main(String[] args) throws Exception {
		PairKey pairKey = TrustSDK.generatePairKey(true);
		System.out.println(JSONObject.toJSONString(pairKey));
		String correctPubKey = ECDSAAlgorithm.generatePublicKey(pairKey.getPrivateKey().trim());
		if (pairKey.getPublicKey().trim().equals(correctPubKey)) {
			System.out.println(true);
		}
	}

}
