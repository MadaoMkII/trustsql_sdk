package com.tencent.trustsql.sdk.module.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommonRequestModel {
    private String mch_sign;
    private String time_stamp;
    private String seq_no;
}
