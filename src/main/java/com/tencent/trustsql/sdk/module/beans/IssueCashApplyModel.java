package com.tencent.trustsql.sdk.module.beans;

import com.alibaba.fastjson.JSONObject;
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

import java.util.Map;
import java.util.TreeMap;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class IssueCashApplyModel extends BaseRequestModel {


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
    private Integer amount;

    @JsonProperty
    private String unit;

    @JsonProperty
    private String asset_type;

    @JsonProperty
    private String owner_account;

    @JsonProperty
    private String content;
    @JsonProperty
    private String mch_sign;
    @JsonProperty
    private Long timestamp;

    @JsonCreator
    @Builder
    public IssueCashApplyModel(String node_id, String chain_id, String ledger_id, Integer amount, String unit, String
            asset_type, String owner_account, String content) {
        this.node_id = node_id;
        this.chain_id = chain_id;
        this.ledger_id = ledger_id;
        this.amount = amount;
        this.unit = unit;
        this.asset_type = asset_type;
        this.owner_account = owner_account;
        this.content = content;
        this.version = "1.0";
        this.sign_type = "ECDSA";
    }

    //    @JsonGetter
    //    public String getContent() {
    //        return JSONObject.parse(content).toString();
    //    }

    @Override
    public Map<String, Object> finalizeModel(EnvironmentConfig environmentConfig) throws Exception {
        //super.setSeq_no(initial_seq_no());
        timestamp = initial_time_stamp();
        setMch_id(environmentConfig.getMch_id());

        ObjectMapper mapper = new ObjectMapper();

        @SuppressWarnings("unchecked")
        Map<String, Object> result = mapper.convertValue(this, TreeMap.class);

        result.put("requestUrlParam","issuecashapply");
        result.put("content",JSONObject.parse(this.content));
//        this.mch_sign = TrustSDK
//                .signString(environmentConfig.getPriKey(), SignStrUtil.mapToKeyValueStr(result).getBytes("UTF-8"),
//                            false);
//        result.put("mch_sign",this.mch_sign);
        return result;

    }
}
