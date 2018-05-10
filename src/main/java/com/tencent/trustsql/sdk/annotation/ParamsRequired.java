package com.tencent.trustsql.sdk.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * check if it is null
 *
 * @author shaun
 * @create 2018-05-06
 **/

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamsRequired {

    /**
     * @return true
     */
    boolean requrie() default true;

}