package com.tencent.trustsql.sdk.module.beans;

import com.fasterxml.jackson.annotation.*;
import com.tencent.trustsql.sdk.annotation.ParamsRequired;
import com.tencent.trustsql.sdk.module.RequestOperator;
import com.tencent.trustsql.sdk.module.beans.CommonRequestModel;
import lombok.*;
import org.springframework.beans.factory.annotation.Configurable;

@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Configurable(preConstruction = true)
public class UserRequestModel extends CommonRequestModel implements RequestOperator {



    @ParamsRequired
    @JsonProperty
    private String sign;


    @ParamsRequired
    @JsonProperty
    private String product_code;

    @ParamsRequired
    @JsonProperty
    private String type;

    @ParamsRequired
    @JsonProperty
    private String req_data;

    @Builder
    @JsonCreator
    public UserRequestModel(String mch_sign, String time_stamp, String seq_no) {
        super(mch_sign, time_stamp, seq_no);
    }

    @JsonGetter("sign")
    public String getSign() {
        //return this.sign;
        return "!35v";
    }
}
