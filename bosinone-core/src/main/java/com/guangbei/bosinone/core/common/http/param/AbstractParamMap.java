package com.guangbei.bosinone.core.common.http.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.guangbei.bosinone.core.manager.model.PPDLoginRequest;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by xugang on 17/1/4.
 */
public abstract class AbstractParamMap implements ParamMapable {

    private static Map<Class<?>, Class<?>> typeConverter = new HashMap<Class<?>, Class<?>>();

    static {
        typeConverter.put(Date.class, Date.class);
        typeConverter.put(List.class, List.class);
        typeConverter.put(ArrayList.class, List.class);
    }

    @Override
    public Map<String, Object> toParamMap() {
        Map<String, Object> map = new HashMap<>(16);
        try {
            Class clazz = this.getClass();
            Method[] methods = clazz.getMethods();

            for (int i = 0; i < methods.length; i++) {
                Method method = methods[i];
                String methodName = method.getName();
                if (!(methodName.startsWith("get") || methodName.startsWith("is")) || methodName.startsWith("getClass")) {
                    continue;
                }
                method.setAccessible(true);
                Object value = method.invoke(this);
                Class type = method.getReturnType();
                //field name

                String propertyName = null;
                if(methodName.startsWith("get")){
                    propertyName = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
                }else if(methodName.startsWith("is")){
                    propertyName = methodName.substring(2, 3).toLowerCase() + methodName.substring(3);
                }

                //注解处理
                boolean methodHasAnno = method.isAnnotationPresent(JsonProperty.class);
                if(methodHasAnno){
                    //得到注解
                    JsonProperty methodAnno = method.getAnnotation(JsonProperty.class);
                    //输出注解属性
                    String annoValue = methodAnno.value();
                    if(StringUtils.isNotEmpty(annoValue)){
                        propertyName = annoValue;
                    }
                }
                if (typeConverter.get(type) == List.class) {
                    List list = (List) value;
                    if (list != null) {
                    } else {
                    }
                } else {
                    if (value != null) {
                        map.put(propertyName, value);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return map;
    }

    public static void main(String[] args) {
        PPDLoginRequest loginRequest = new PPDLoginRequest();
        loginRequest.setUserName("12121");
        loginRequest.setPassword("eeweq");
        loginRequest.setRememberMe("false");
        System.out.println(loginRequest.toParamMap());
    }
}
