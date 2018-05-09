package com.tencent.trustsql.sdk.command;

import com.tencent.trustsql.sdk.config.ErrorNum;
import com.tencent.trustsql.sdk.config.TrustSDK;
import com.tencent.trustsql.sdk.exception.TrustSDKException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

public class EncryptDES3Command implements Command {

	@Override
	public String execute(String... args) throws Exception {
		if (args == null || args.length < 3 || StringUtils.isEmpty(args[1]) || StringUtils.isEmpty(args[2])) {
			throw new TrustSDKException(ErrorNum.INVALID_PARAM_ERROR.getRetCode(),
					ErrorNum.INVALID_PARAM_ERROR.getRetMsg());
		}
		String key = args[1];
		String data = args[2];
		System.out.println(String.format("des param key [%s] and data [%s] :", key, data));
		byte[] bytes = TrustSDK.encryptDES3(key, data.getBytes("UTF-8"));
		return Base64.encodeBase64String(bytes);
	}

}
