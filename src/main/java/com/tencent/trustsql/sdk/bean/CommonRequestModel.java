package com.tencent.trustsql.sdk.bean;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public class CommonRequestModel {
    private String mch_sign;
    private String time_stamp;
    private String seq_no;
}
