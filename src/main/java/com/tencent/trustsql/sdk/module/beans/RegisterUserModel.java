package com.tencent.trustsql.sdk.module.beans;


import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class RegisterUserModel extends BaseRequestModel  {


    @Builder
    @JsonCreator
    public RegisterUserModel(String time_stamp, String seq_no, String operationName, String sign, String
            product_code, String type, String pub_key, String user_fullName, String user_id) {
        super(time_stamp, seq_no, operationName);
        this.sign = sign;
        this.product_code = product_code;
        this.type = type;
        assembleReq_data(pub_key, user_fullName, user_id);

    }


    @JsonProperty
    private String sign;


    @JsonProperty
    private String product_code;


    @JsonProperty
    private String type;


    @JsonProperty
    private String req_data;

    @JsonIgnore
    @JsonProperty
    private String user_fullName;

    @JsonProperty
    @JsonIgnore
    private String user_id;

    public void assembleReq_data(final String pubKey, final String user_fullName, final String user_id) {

        req_data = "{\"public_key\":\"" + pubKey + "\",\"user_fullName\":\"" + user_fullName + "\"," +
                "\"user_id\":\"" + user_id + "\"}";

    }

}
