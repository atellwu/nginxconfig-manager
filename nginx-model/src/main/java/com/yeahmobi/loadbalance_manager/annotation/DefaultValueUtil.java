package com.yeahmobi.loadbalance_manager.annotation;

import java.lang.reflect.Field;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class DefaultValueUtil {

    public static void resolveDefaultValue(Object bean) throws IllegalArgumentException, IllegalAccessException {
        BeanWrapper wrapper = new BeanWrapperImpl(bean);

        Class clazz = bean.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.getAnnotation(DefaultValue.class) != null) {
                Object propertyValue = wrapper.getPropertyValue(field.getName());
                // 如果有注解，且没有值，则设置默认值
                if (propertyValue == null) {
                    DefaultValue defaultValue = field.getAnnotation(DefaultValue.class);
                    String value = defaultValue.value();
                    wrapper.setPropertyValue(field.getName(), value);
                }
            }
        }
    }

}
