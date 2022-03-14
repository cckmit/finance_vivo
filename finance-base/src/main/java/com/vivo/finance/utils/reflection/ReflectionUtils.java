package com.vivo.finance.utils.reflection;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class ReflectionUtils {


    public static List<Field> getAllFields(Class clazz, List<Field> fields) {
        if (clazz != null) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            getAllFields(clazz.getSuperclass(), fields);
        }
        return fields;
    }
}
