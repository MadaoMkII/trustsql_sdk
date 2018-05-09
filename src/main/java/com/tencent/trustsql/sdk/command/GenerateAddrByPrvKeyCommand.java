package com.tencent.trustsql.sdk.command;

import com.tencent.trustsql.sdk.config.ErrorNum;
import com.tencent.trustsql.sdk.config.TrustSDK;
import com.tencent.trustsql.sdk.exception.TrustSDKException;
import org.apache.commons.lang3.StringUtils;

public class GenerateAddrByPrvKeyCommand implements Command {
	
	@Override
	public String execute(String... args) throws Exception {
		if(args == null || args.length < 2 || StringUtils.isEmpty(args[1])) {
			throw new TrustSDKException(ErrorNum.INVALID_PARAM_ERROR.getRetCode(), ErrorNum.INVALID_PARAM_ERROR.getRetMsg());
		}
//		JSONArray jsonArr = JSONArray.parseArray(args[1]);
//		String prvKey = jsonArr.getString(0);
		String prvKey = args[1];
		System.out.println(String.format("Calculate the address by private key [%s]:", prvKey));
		String prvKeyAddr = TrustSDK.generateAddrByPrvkey(prvKey);
		return prvKeyAddr;
	}

	public static void main(String[] args) throws TrustSDKException {
		String pubKeyAddr = TrustSDK.generateAddrByPrvkey(
				"vL/lFwaGGRxG0ND13xu1XxQK7qP6ny3Kt3LLLKWn8kM=");
		System.out.println(pubKeyAddr);
	}
	
}
