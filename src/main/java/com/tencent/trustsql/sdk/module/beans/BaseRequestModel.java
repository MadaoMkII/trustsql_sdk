package com.tencent.trustsql.sdk.module.beans;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tencent.trustsql.sdk.annotation.ValueRequired;
import com.tencent.trustsql.sdk.config.EnvironmentConfig;
import com.tencent.trustsql.sdk.module.RequestOperator;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;


@Getter
@Setter
@RequiredArgsConstructor
public abstract class BaseRequestModel implements RequestOperator {
    @NonNull
    @ValueRequired
    private String time_stamp;
    @NonNull
    @ValueRequired
    private String seq_no;

    @ValueRequired
    private String mch_id;




}
