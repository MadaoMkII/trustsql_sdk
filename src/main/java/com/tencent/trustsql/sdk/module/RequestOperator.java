package com.tencent.trustsql.sdk.module;

import com.alibaba.fastjson.JSONObject;
import com.tencent.trustsql.sdk.annotation.ValueRequired;
import com.tencent.trustsql.sdk.config.EnvironmentConfig;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;


public interface RequestOperator {

    default boolean validate() throws Exception {

        Field[] thisFields = this.getClass().getDeclaredFields();
        Field[] superFields = this.getClass().getSuperclass().getDeclaredFields();

        Field[] fields = Arrays.copyOf(thisFields, thisFields.length + superFields.length);

        System.arraycopy(superFields, 0, fields, thisFields.length, superFields.length);

        for (Field field : fields) {
            if (field.isAnnotationPresent(ValueRequired.class)) {
                ValueRequired paramsRequired = field.getAnnotation(ValueRequired.class);
                if (paramsRequired.requrie()) {
                    // 如果类型是String
                    if (field.getGenericType().toString()
                            .equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
                        // 拿到该属性的getter方法
                        /*
                          这里需要说明一下：他是根据拼凑的字符来找你写的getter方法的
                          在Boolean值的时候是isXXX（默认使用ide生成getter的都是isXXX）
                          如果出现NoSuchMethod异常 就说明它找不到那个gettet方法 需要做个规范
                         */
                        byte[] items = field.getName().getBytes();
                        items[0] = (byte) ((char) items[0] - 'a' + 'A');
                        String methodName = new String(items);
                        Method m = this.getClass().getMethod("get" + methodName);

                        String val = (String) m.invoke(this);// 调用getter方法获取属性值
                        if (StringUtils.isEmpty(val)) {
                            System.out.println("Value lost is :" + field.getName());
                            return false;
                            //throw new ParamsNullException(field.getName() + " 不能为空!");
                        } else {
                            System.out.println("String type:" + val);
                        }
                    }
                }

            }
        }
        return true;
    }


    //    /**
    //     * 把一个字符串的第一个字母大写、效率是最高的、
    //     */
    //    default String getMethodName(String filedName) {
    //        byte[] items = filedName.getBytes();
    //        items[0] = (byte) ((char) items[0] - 'a' + 'A');
    //        return new String(items);
    //    }

    default Long initial_time_stamp() {
        return System.currentTimeMillis() / 1000;

    }

    default String initial_seq_no() {
        return System.currentTimeMillis() + "0000000000001700000";

    }

    default String getRequestUrlParam() {
        return this.getClass().getSimpleName().replace("Model", "").toLowerCase();

    }

    Map<String, Object> finalizeModel(EnvironmentConfig environmentConfig) throws Exception;

    default String toJsonNode() {

        return JSONObject.toJSON(this).toString();


    }
}
