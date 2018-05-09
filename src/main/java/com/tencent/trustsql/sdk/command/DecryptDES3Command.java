package com.tencent.trustsql.sdk.command;

import com.tencent.trustsql.sdk.config.ErrorNum;
import com.tencent.trustsql.sdk.config.TrustSDK;
import com.tencent.trustsql.sdk.exception.TrustSDKException;
import org.apache.commons.lang3.StringUtils;
import org.spongycastle.util.encoders.Base64;

public class DecryptDES3Command implements Command {

	@Override
	public String execute(String... args) throws Exception {
		if (args == null || args.length < 3 || StringUtils.isEmpty(args[1]) || StringUtils.isEmpty(args[2])) {
			throw new TrustSDKException(ErrorNum.INVALID_PARAM_ERROR.getRetCode(),
					ErrorNum.INVALID_PARAM_ERROR.getRetMsg());
		}
		String key = args[1];
		byte[] data = Base64.decode(args[2]);
		System.out.println(String.format("des param key [%s] and data [%s] :", key, args[2]));
		byte[] bytes = TrustSDK.decryptDES3(key, data);
		return new String(bytes,"UTF-8");
	}
	
}
