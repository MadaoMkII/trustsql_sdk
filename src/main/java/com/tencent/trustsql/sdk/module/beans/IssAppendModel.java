package com.tencent.trustsql.sdk.module.beans;


import com.fasterxml.jackson.annotation.*;
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

public class IssAppendModel extends BaseRequestModel {

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
    @JsonIgnore
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

    @JsonProperty
    private String public_key;
    @JsonProperty
    private String sign;
    @JsonProperty
    private String mch_sign;

    @Builder
    @JsonCreator
    public IssAppendModel(String version, String node_id, String chain_id, String ledger_id, String info_version,
                          String state, String content, String accountPriKey, String sign, String notes) {

        this.version = version;
        sign_type = "ECDSA";
        this.node_id = node_id;
        this.chain_id = chain_id;
        this.ledger_id = ledger_id;

        this.info_version = info_version;
        this.state = state;
        this.content = content;

        this.notes = notes;
        this.accountPriKey = accountPriKey;
        this.sign = sign;
    }


    @Override
    public Map<String, Object> finalizeModel(EnvironmentConfig environmentConfig) throws Exception {
        super.setSeq_no(initial_seq_no());
        //super.setTime_stamp(initial_time_stamp());
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
        return null;

    }


}
