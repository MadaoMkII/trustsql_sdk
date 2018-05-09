package com.tencent.trustsql.sdk.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;
@JsonPOJOBuilder
@Data
public class RequestModelException extends Exception {

    private static final long serialVersionUID = -0;

    @JsonProperty
    private String rtnCd;
    @JsonProperty
    private String rtnMsg;


}
