package com.tencent.trustsql.sdk.module.beans;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tencent.trustsql.sdk.annotation.ValueRequired;
import com.tencent.trustsql.sdk.module.RequestOperator;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
public class BaseRequestModel implements RequestOperator {


    private String time_stamp;
    @ValueRequired
    private String seq_no;

    @JsonIgnore
    private String operationName;


}
