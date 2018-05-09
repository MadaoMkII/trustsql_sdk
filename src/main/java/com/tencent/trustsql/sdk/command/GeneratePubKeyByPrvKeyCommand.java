package com.tencent.trustsql.sdk.command;

import com.tencent.trustsql.sdk.config.ErrorNum;
import com.tencent.trustsql.sdk.config.TrustSDK;
import com.tencent.trustsql.sdk.exception.TrustSDKException;
import org.apache.commons.lang3.StringUtils;

public class GeneratePubKeyByPrvKeyCommand implements Command {

	@Override
	public String execute(String... args) throws Exception {
		if (args == null || args.length < 2 || StringUtils.isEmpty(args[1])) {
			throw new TrustSDKException(ErrorNum.INVALID_PARAM_ERROR.getRetCode(),
					ErrorNum.INVALID_PARAM_ERROR.getRetMsg());
		}
		// JSONArray jsonArr = JSONArray.parseArray(args[1]);
		// String prvKey = jsonArr.getString(0);
		String prvKey = args[1];
		System.out.println(String.format("Convert the private key [%s] to public key:", prvKey));
		String pubKey = TrustSDK.generatePubkeyByPrvkey(prvKey, true);
		return pubKey;
	}

	public static void main(String[] args) throws TrustSDKException {
		String pubKey = TrustSDK.generatePubkeyByPrvkey("mqjV0NuX0D+EM5wY1EOY4XfDt8YKdhWY20BxT9FGTCE=", true);
		System.out.println(pubKey);
		boolean flag = TrustSDK.checkPairKey("TpWgEhH0NFIw17TaQHNltW7oP+YQQC6+3/s2j9muMc8=",
				"BCuY3/nyi/KM0356A9ELiYAxxJhKbXMrvhglYCccMgoScCsKtDuytnkoPpOmR1GJN2Auo8vp/UAXaWuQaAUX/k8=");
		System.out.println(flag);
		String decodePubKey = TrustSDK.decodePubkey("AiKIkA4RsMS2GOrquWZQ4/s4U3XChFSRcLJMwmnXI4mu");
		System.out.println(decodePubKey);

	}

}
