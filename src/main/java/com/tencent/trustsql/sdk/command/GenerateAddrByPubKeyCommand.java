package com.tencent.trustsql.sdk.command;

import com.tencent.trustsql.sdk.config.ErrorNum;
import com.tencent.trustsql.sdk.config.TrustSDK;
import com.tencent.trustsql.sdk.exception.TrustSDKException;
import org.apache.commons.lang3.StringUtils;

public class GenerateAddrByPubKeyCommand implements Command {

	@Override
	public String execute(String... args) throws Exception {
		if (args == null || args.length < 2 || StringUtils.isEmpty(args[1])) {
			throw new TrustSDKException(ErrorNum.INVALID_PARAM_ERROR.getRetCode(),
					ErrorNum.INVALID_PARAM_ERROR.getRetMsg());
		}
		// JSONArray jsonArr = JSONArray.parseArray(args[1]);
		// String pubKey = jsonArr.getString(0);
		String pubKey = args[1];
		System.out.println(String.format("Calculate the address by public key [%s]:", pubKey));
		String pubKeyAddr = TrustSDK.generateAddrByPubkey(pubKey);
		return pubKeyAddr;
	}

	public static void main(String[] args) throws TrustSDKException {
		String pubKeyAddr = TrustSDK.generateAddrByPubkey(
				"Aj56Q9WYRtRhv7EVGmm29mOmLYSSPdMaoH9lchnGJM1x");
		System.out.println(pubKeyAddr);
	}

}
