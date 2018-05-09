package com.tencent.trustsql.sdk.command;

import com.tencent.trustsql.sdk.config.ErrorNum;
import com.tencent.trustsql.sdk.config.TrustSDK;
import com.tencent.trustsql.sdk.exception.TrustSDKException;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

public class VerifyRenSignCommand implements Command {

	@Override
	public String execute(String... args) throws Exception {
		if (args == null || args.length < 4 || StringUtils.isEmpty(args[1]) || StringUtils.isEmpty(args[2])) {
			throw new TrustSDKException(ErrorNum.INVALID_PARAM_ERROR.getRetCode(),
					ErrorNum.INVALID_PARAM_ERROR.getRetMsg());
		}

		String pubKey = args[1];
		String data = args[2];
		String sign = args[3];
		try {
			boolean flag = TrustSDK.verifyString(pubKey, data, sign);
			System.out.println("flag:" + flag);
			return Boolean.toString(flag);
		} catch (Exception e) {
			throw new TrustSDKException(ErrorNum.INVALID_PARAM_ERROR.getRetCode(),
					ErrorNum.INVALID_PARAM_ERROR.getRetMsg());
		}
	}

	public static void main(String[] args) throws TrustSDKException, DecoderException {
		boolean flag = TrustSDK.verifyRenSign("A7UZN8gSdR04QhOvTyU7c7oBIzRoQltx5bB+XYwkRke6",
				Hex.decodeHex("e940871aa7a348c17a68f3486caced653a8cae9c1a514d0b7855848f8254b335".toCharArray()),
				"MEUCIQD3KKeEucqLUbKSdjzqKeBLTR57DzHDlDakhgCn1sILdgIgPDzkPnJIHVkG0dsD36BigBRvmAPOEESPvLqR5vqk3lA=");
		System.out.println("flag:" + flag);
	}

}
