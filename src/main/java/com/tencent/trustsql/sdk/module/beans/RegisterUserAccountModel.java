package com.tencent.trustsql.sdk.module.beans;


import com.fasterxml.jackson.annotation.*;
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

public class RegisterUserAccountModel extends BaseRequestModel {


    @JsonProperty
    private String sign;
    @JsonProperty
    private String product_code;
    @JsonProperty
    private String type;
    @JsonProperty
    private String req_data;
    @JsonIgnore
    private String user_id;
    @JsonIgnore
    private String pubKey;

    @Builder
    @JsonCreator
    public RegisterUserAccountModel(String product_code, String user_id, String pubKey) {

        this.product_code = product_code;
        this.type = "sign";
        this.pubKey = pubKey;
        this.user_id = user_id;

    }

    private String assembleReq_data(final String pubKey, final String user_id) {

        return "{\"public_key\":\"" + pubKey + "\"," + "\"user_id\":\"" + user_id + "\"}";

    }

    @Override
    public Map<String, Object> finalizeModel(EnvironmentConfig environmentConfig) throws Exception {
        super.initial_seq_no();
        super.initial_time_stamp();
        setMch_id(environmentConfig.getMch_id());
        ObjectMapper mapper = new ObjectMapper();
        @SuppressWarnings("unchecked")
        Map<String, Object> result = mapper.convertValue(this.toJsonNode(), TreeMap.class);
        result.put("req_data", assembleReq_data(pubKey, this.getUser_id()));
        this.sign = TrustSDK
                .signString(environmentConfig.getPriKey(), SignStrUtil.mapToKeyValueStr(result).getBytes(), false);
        req_data = (String) result.get("req_data");

        return null;

    }
}
