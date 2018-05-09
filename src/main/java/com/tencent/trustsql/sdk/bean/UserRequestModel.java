package com.tencent.trustsql.sdk.bean;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserRequestModel extends CommonRequestModel {
    @JsonProperty
    private String sign;


    @Builder
    @JsonCreator
    public UserRequestModel(String mch_sign, String time_stamp, String seq_no) {
        super(mch_sign, time_stamp, seq_no);
    }


}
