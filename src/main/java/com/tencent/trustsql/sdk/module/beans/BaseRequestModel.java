package com.tencent.trustsql.sdk.module.beans;


import com.tencent.trustsql.sdk.annotation.ValueRequired;
import com.tencent.trustsql.sdk.module.RequestOperator;
import lombok.*;


@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
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
