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

public class RegisterUserModel extends BaseRequestModel {


    @JsonProperty
    private String sign;
    @JsonProperty
    private String product_code;
    @JsonProperty
    private String type = "sign";
    @JsonProperty
    private String req_data;
    @JsonIgnore
    private String user_fullName;
    @JsonIgnore
    private String user_id;

    @Builder
    @JsonCreator
    public RegisterUserModel(String product_code, String user_fullName, String user_id) {

        this.sign = null;
        this.product_code = product_code;
        this.user_fullName = user_fullName;
        this.user_id = user_id;

    }

    private void assembleReq_data(final String pubKey, final String user_fullName, final String user_id) {

        this.req_data = "{\"public_key\":\"" + pubKey + "\",\"user_fullName\":\"" + user_fullName + "\"," +
                "\"user_id\":\"" + user_id + "\"}";

    }

    @Override
    public void finalizeModel(EnvironmentConfig environmentConfig) throws Exception {
        super.initial_seq_no();
        super.initial_time_stamp();
        setMch_id(environmentConfig.getMch_id());
        assembleReq_data(environmentConfig.getPubKey(), user_fullName, user_id);
        ObjectMapper mapper = new ObjectMapper();
        @SuppressWarnings("unchecked")
        Map<String, Object> result = mapper.convertValue(this.toJsonNode(), TreeMap.class);
        this.sign = TrustSDK
                .signString(environmentConfig.getPriKey(), SignStrUtil.mapToKeyValueStr(result).getBytes("UTF-8"),
                            false);
        req_data = (String) result.get("req_data");
    }
}
