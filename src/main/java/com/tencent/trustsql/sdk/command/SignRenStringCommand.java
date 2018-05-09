package com.tencent.trustsql.sdk.command;

import com.tencent.trustsql.sdk.config.ErrorNum;
import com.tencent.trustsql.sdk.config.TrustSDK;
import com.tencent.trustsql.sdk.exception.TrustSDKException;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

public class SignRenStringCommand implements Command {

	@Override
	public String execute(String... args) throws Exception {
		if (args == null || args.length < 3 || StringUtils.isEmpty(args[1]) || StringUtils.isEmpty(args[2])) {
			throw new TrustSDKException(ErrorNum.INVALID_PARAM_ERROR.getRetCode(),
					ErrorNum.INVALID_PARAM_ERROR.getRetMsg());
		}

		String privateKey = args[1];
		String data = args[2];
		try {
			String sign = TrustSDK.SignRenString(privateKey, data.getBytes("UTF-8"));
			System.out.println("sign:" + sign);
			return sign;
		} catch (Exception e) {
			throw new TrustSDKException(ErrorNum.INVALID_PARAM_ERROR.getRetCode(),
					ErrorNum.INVALID_PARAM_ERROR.getRetMsg());
		}
	}

	public static void main(String[] args) throws TrustSDKException, DecoderException {
		String sign = TrustSDK.SignRenString(" FCVDyc4UDT7lWAxk0OGssOznXZqajVLTn3lzoPtKvC4",
				Hex.decodeHex("be432e48117b912ae6d25030f2de1776f4493138dc9bc7828b48f08d3f96a569".toCharArray()));
		System.out.println("sign:" + sign);
	}

}
