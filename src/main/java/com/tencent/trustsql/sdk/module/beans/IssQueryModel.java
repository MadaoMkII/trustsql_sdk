package com.tencent.trustsql.sdk.module.beans;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tencent.trustsql.sdk.config.EnvironmentConfig;
import com.tencent.trustsql.sdk.config.TrustSDK;
import com.tencent.trustsql.sdk.util.SignStrUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class IssQueryModel extends BaseRequestModel {


    //{
    //	"content": {
    //		"owner": "ulegal"
    //	},
    //	"mch_id": "gbec7b7cece75c8a5",
    //	"notes": {
    //		"extInfo": "default"
    //	},
    //	"page_limit": "2",
    //	"page_no": "1",
    //	"sign_type": "ECDSA",
    //	"timestamp": "1503648096",
    //	"version": "1.0",
    //	"mch_sign": "MEYCIQDCoCYth2zGer2Z/kliD11jRXGKqLqLNk/vo18js+CvRwIhANTQ3PbN9vj9YjmaB+rma2Sz0D+30WgZPOHAO9ysRsj1"
    //}

    @JsonProperty
    private String accountPriKey;

    @JsonProperty
    private String version;
    @JsonProperty
    private String sign_type;
    @JsonProperty
    private String node_id;
    @JsonProperty
    private String chain_id;
    @JsonProperty
    private String ledger_id;
    @JsonProperty
    private String info_key;

    @JsonProperty
    private String info_version;
    @JsonProperty
    private String state;
    @JsonProperty
    private String content;
    @JsonProperty
    private String notes;

    @JsonProperty
    private String commit_time;
    @JsonProperty
    private String account;

    //区块高度	b_height	否	JsonObject	条件范围，区块高度范围
    //记录时间	commit_time	否	JsonObject	条件范围，记录时间范围
    @JsonProperty
    private String range;

    @JsonProperty
    private String t_hash;

    @JsonProperty
    private int page_no;

    @JsonProperty
    private int page_limit;

    @JsonProperty
    private String public_key;

    @JsonProperty
    private String sign;

    @JsonProperty
    private String mch_sign;

    @Builder
    @JsonCreator
    public IssQueryModel(String time_stamp, String seq_no, String accountPriKey, String version, String sign_type,
                         String node_id, String chain_id, String ledger_id, String info_key, String info_version,
                         String state, String content, String notes, String commit_time, String account, String
                                     range, String t_hash, int page_no, int page_limit, String public_key, String
                                     sign, String mch_sign) {

        this.accountPriKey = accountPriKey;
        this.version = version;
        this.sign_type = sign_type;
        this.node_id = node_id;
        this.chain_id = chain_id;
        this.ledger_id = ledger_id;
        this.info_key = info_key;
        this.info_version = info_version;
        this.state = state;
        this.content = content;
        this.notes = notes;
        this.commit_time = commit_time;
        this.account = account;
        this.range = range;
        this.t_hash = t_hash;
        this.page_no = page_no;
        this.page_limit = page_limit;
        this.public_key = public_key;
        this.sign = sign;
        this.mch_sign = mch_sign;
    }

    @Override
    public void finalizeModel(EnvironmentConfig environmentConfig) throws Exception {
        super.setSeq_no(initial_seq_no());
        super.setTime_stamp(initial_time_stamp());
        setMch_id(environmentConfig.getMch_id());
        ObjectMapper mapper = new ObjectMapper();

        info_key = String.valueOf(System.currentTimeMillis() / 1000);
        account = TrustSDK.generateAddrByPrvkey(accountPriKey);
        public_key = TrustSDK.generatePubkeyByPrvkey(accountPriKey, true);

        String pattern = "YYYY-MM-dd HH:mm:SS";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        commit_time = simpleDateFormat.format(new Date());

        @SuppressWarnings("unchecked")
        Map<String, Object> result = mapper.convertValue(this.toJsonNode(), TreeMap.class);
        this.sign = TrustSDK
                .signString(environmentConfig.getPriKey(), SignStrUtil.mapToKeyValueStr(result).getBytes(), false);


    }


}
