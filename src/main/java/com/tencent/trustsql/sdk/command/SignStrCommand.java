package com.tencent.trustsql.sdk.command;

import com.tencent.trustsql.sdk.config.ErrorNum;
import com.tencent.trustsql.sdk.config.TrustSDK;
import com.tencent.trustsql.sdk.exception.TrustSDKException;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class SignStrCommand implements Command {

    @Override
    public String execute(String... args) throws Exception {
        if (args == null || args.length < 3 || StringUtils.isEmpty(args[1]) || StringUtils.isEmpty(args[2])) {
            throw new TrustSDKException(ErrorNum.INVALID_PARAM_ERROR.getRetCode(),
                                        ErrorNum.INVALID_PARAM_ERROR.getRetMsg());
        }
        String prvKey = args[1];
        String srcStr = args[2];
        try {
            System.out.println(String.format("Sign a string [%s] with private key [%s]: ", srcStr, prvKey));
            String sign = TrustSDK.signString(prvKey, srcStr.getBytes("UTF-8"), false);
            return sign;
        } catch (Exception e) {
            throw new TrustSDKException(ErrorNum.INVALID_PARAM_ERROR.getRetCode(),
                                        ErrorNum.INVALID_PARAM_ERROR.getRetMsg());
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException, TrustSDKException,
			NoSuchAlgorithmException, NoSuchPaddingException {
        String sign = TrustSDK.SignRenString("TpWgEhH0NFIw17TaQHNltW7oP+YQQC6+3/s2j9muMc8=",
                                          ("asset_id=26aQMZWZdhLoeYqcVDQr2NLYou1wePanEewQN49SSA9zQ3t&asset_type=1" +
												  "&chain_id=ch_tencent_test&ledger_id=ld_tencent_dam&mch_id" +
												  "=gba363879924b43db&node_id=nd_tencent_test1&sign_list=[{\"sign" +
												  "\":\"MEQCIGjenZFm9uej0I/kTZ9o5daCijMLk1ctwMqCnVY/EzauAiAauv+KUQLk" +
												  "/9rYFruTka7T37x2Qb3ovrzL1ktECQRlVg==\",\"id\":1," +
												  "\"sign_str\":\"1d168d72c7c470f84baf0715c88ae413b331779171d5b6b1b03d8fe38a46fb52\",\"account\":\"1D2QMtcGMdGdW21bx7r3Sm75R6tS3bt8vY\"}]&sign_type=ECDSA&timestamp=1522661354&transaction_id=201804021440010374&version=1.0").getBytes(
                                                  "UTF-8"));
        System.out.println(sign);
    }

}
