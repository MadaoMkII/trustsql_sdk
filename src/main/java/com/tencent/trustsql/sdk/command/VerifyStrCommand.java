package com.tencent.trustsql.sdk.command;

import com.tencent.trustsql.sdk.config.ErrorNum;
import com.tencent.trustsql.sdk.config.TrustSDK;
import com.tencent.trustsql.sdk.exception.TrustSDKException;
import org.apache.commons.lang3.StringUtils;

public class VerifyStrCommand implements Command {

	@Override
	public String execute(String... args) throws Exception {
		if (args == null || args.length < 4 || StringUtils.isEmpty(args[1]) || StringUtils.isEmpty(args[2])
				|| StringUtils.isEmpty(args[3])) {
			throw new TrustSDKException(ErrorNum.INVALID_PARAM_ERROR.getRetCode(),
					ErrorNum.INVALID_PARAM_ERROR.getRetMsg());
		}
		String pubKey = args[1];
		String srcStr = args[2];
		String sign = args[3];
		System.out.println(String.format("Verify a sign [%s] with pubkey key [%s] :", srcStr, pubKey));
		boolean isCorrect = TrustSDK.verifyString(pubKey, srcStr, sign);
		return Boolean.toString(isCorrect);
	}

	public static void main(String[] args) throws TrustSDKException {
		boolean isCorrect = TrustSDK.verifyString(
				"BPiQW6TrEdPklaEQ90k30NVxoyNQqJrVfIjUvSHBrtGYg+OmaMPX4DIVnblu4BHdyPsr4aaeQpT2fD1PDvIqL7w=",
				"asset_id=26aEmdgHGKosydSDhzvudpXfcfyY4h4A4rXGhjbSF8i4wrE&asset_type=6&chain_id=ch_tencent_test&ledger_id=ld_tencent_iss&mch_id=gb4aa160f427a8745&node_id=nd_tencent_test1&sign_list=[{\"sign\":\"MEUCIQClHMCbGjR/WU0lJAa5lzmbXAcqh1WjXD1AA6dR+7i6bQIgO2ddkAibP/VGjYLC/vyt4kqEyIbJDOYI8MTTtSN/aCM=\",\"id\":\"1\",\"sign_str\":\"34c717f86b08fca3cdacdfb16974d845456adc753de01e9cf52f4655c8d6475e\",\"account\":\"12P5GfV3DaWVuNdato1ZG37YmbA3egHmR4\"}]&sign_type=ECDSA&timestamp=1522832384&transaction_id=201804041440011205&version=1.0",
				"MEQCIGdducj7YhHXTUVrpWOwjMFB9laAb7GW0avVQ1pXCqwzAiA2Eb3JDrrqqHgDrRaiMrrKeUeSntJqA3YOgqovDc3ZxA==");
		System.out.println(isCorrect);
	}

}
