package com.tencent.trustsql.sdk.command;

public class CommandFactory {

	public static final String CMD_ISSAPPEND = "tIssAppend";
	public static final String CMD_ISSQUERY = "tIssQuery";
	public static final String CMD_GENERATE_PAIRKEY = "tGeneratePairkey";
	public static final String CMD_CHECK_PAIRKEY = "tCheckPairkey";
	public static final String CMD_GENERATE_PUBKEY_BY_PRVKEY = "tGeneratePubkeyByPrvkey";
	public static final String CMD_GENERATE_ADDR_BY_PUBKEY = "tGenerateAddrByPubkey";
	public static final String CMD_GENERATE_ADDR_BY_PRVKEY = "tGenerateAddrByPrvkey";
	public static final String CMD_SIGN_STR = "tSignString";
	public static final String CMD_VERIFY_STR = "tVerifySign";
	public static final String CMD_ENCRYPT_DES3 = "tEncryptDes3";
	public static final String CMD_DECRYPT_DES3 = "tDecryptDes3";
	public static final String CMD_ISS_SIGN = "tSignRenString";
	public static final String CMD_ISS_VERIFY = "tVerifyRenSign";
	public static final String CMD_ISSUE_APPLY = "tIssueApply";
	public static final String CMD_ISSUE_SUBMIT = "tIssueSubmit";
	public static final String CMD_ISSUE_TRANSFER_APPLY = "tAssetTransferApply";
	public static final String CMD_ISSUE_TRANSFER_SUBMIT = "tAssetTransferSubmit";
	public static final String CMD_ISSUE_TRANSFER_SIGN_APPLY = "tAssetTransferSignApply";
	public static final String CMD_ISSUE_TRANSFER_SIGN_SUBMIT = "tAssetTransferSignSubmit";
	public static final String CMD_ISSUE_TRANSFER_UNSIGN_APPLY = "tAssetTransferUnSignApply";
	public static final String CMD_ISSUE_TRANSFER_UNSIGN_SUBMIT = "tAssetTransferUnSignSubmit";
	public static final String CMD_ISSUE_CASH_APPLY = "tIssueCashApply";
	public static final String CMD_ISSUE_CASH_SUBMIT = "tIssueCashSubmit";
	public static final String CMD_ISSUE_QUERY = "tIssueQuery";
	public static final String CMD_TRANSACTION_QUERY = "tTransactionQuery";
	public static final String CMD_HELP = "help";
	public static final String RegisterUser = "registerUser";
	public static Command getCommand(String option) {
		if (CMD_ISSAPPEND.equals(option)) {
			return new IssAppendCommand();
		} else if (CMD_ISSQUERY.equals(option)) {
			return new IssQueryCommand();
		} else if (CMD_GENERATE_PAIRKEY.equals(option)) {
			return new GeneratePairKeyCommand();
		} else if (CMD_GENERATE_PUBKEY_BY_PRVKEY.equals(option)) {
			return new GeneratePubKeyByPrvKeyCommand();
		} else if (CMD_GENERATE_ADDR_BY_PUBKEY.equals(option)) {
			return new GenerateAddrByPubKeyCommand();
		} else if (CMD_GENERATE_ADDR_BY_PRVKEY.equals(option)) {
			return new GenerateAddrByPrvKeyCommand();
		} else if (CMD_SIGN_STR.equals(option)) {
			return new SignStrCommand();
		} else if (CMD_VERIFY_STR.equals(option)) {
			return new VerifyStrCommand();
		} else if (CMD_ENCRYPT_DES3.equals(option)) {
			return new EncryptDES3Command();
		} else if (CMD_DECRYPT_DES3.equals(option)) {
			return new DecryptDES3Command();
		} else if (CMD_ISS_SIGN.equals(option)) {
			return new SignRenStringCommand();
		} else if (CMD_ISS_VERIFY.equals(option)) {
			return new VerifyRenSignCommand();
		} else if (CMD_ISSUE_APPLY.equals(option)) {
			return new IssueApplyCommand();
		} else if (CMD_ISSUE_SUBMIT.equals(option)) {
			return new IssueSubmitCommand();
		} else if (CMD_ISSUE_TRANSFER_APPLY.equals(option)) {
			return new AssetTransferApplyCommand();
		} else if (CMD_ISSUE_TRANSFER_SUBMIT.equals(option)) {
			return new AssetTransferSubmitCommand();
		} else if (CMD_ISSUE_TRANSFER_SIGN_APPLY.equals(option)) {
			return new AssetTransferSignApplyCommand();
		} else if (CMD_ISSUE_TRANSFER_SIGN_SUBMIT.equals(option)) {
			return new AssetTransferSignSubmitCommand();
		} else if (CMD_ISSUE_TRANSFER_UNSIGN_APPLY.equals(option)) {
			return new AssetTransferUnSignApplyCommand();
		} else if (CMD_ISSUE_TRANSFER_UNSIGN_SUBMIT.equals(option)) {
			return new AssetTransferUnSignSubmitCommand();
		} else if (CMD_ISSUE_CASH_APPLY.equals(option)) {
			return new IssueCashApplyCommand();
		}else if (CMD_ISSUE_CASH_SUBMIT.equals(option)) {
			return new IssueCashSubmitCommand();
		} else if (CMD_ISSUE_QUERY.equals(option)) {
			return new IssueQueryCommand();
		}  else if (CMD_TRANSACTION_QUERY.equals(option)) {
			return new TransactionQueryCommand();
		} else if (CMD_HELP.equals(option)) {
			return new HelpCommand();
		}else if (RegisterUser.equals(option)) {
			return new RegisterUser();
		}
		return null;
	}

}
