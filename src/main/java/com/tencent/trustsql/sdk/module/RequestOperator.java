package com.tencent.trustsql.sdk.module;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tencent.trustsql.sdk.annotation.ParamsRequired;
import com.tencent.trustsql.sdk.exception.ParamsNullException;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public interface RequestOperator {

    default void validate() throws Exception {

        Field[] fields = this.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(ParamsRequired.class)) {
                ParamsRequired paramsRequired = field.getAnnotation(ParamsRequired.class);
                if (paramsRequired.requrie()) {
                    // 如果类型是String
                    if (field.getGenericType().toString().equals(
                            "class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
                        // 拿到该属性的gettet方法
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
                            throw new ParamsNullException(field.getName() + " 不能为空!");
                        } else {
                            System.out.println("String type:" + val);
                        }
                    }
                }

            } else {

                throw new ParamsNullException("Field is not String type");

            }
        }
    }


    //    /**
    //     * 把一个字符串的第一个字母大写、效率是最高的、
    //     */
    //    default String getMethodName(String filedName) {
    //        byte[] items = filedName.getBytes();
    //        items[0] = (byte) ((char) items[0] - 'a' + 'A');
    //        return new String(items);
    //    }

    default JsonNode toJsonNode() {

        ObjectMapper mapper = new ObjectMapper();

        return mapper.convertValue(this, JsonNode.class);
    }
}
