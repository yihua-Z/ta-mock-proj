package com.psbc.service;

import lombok.NonNull;

import java.lang.reflect.Field;

/**
 * 对象处理器
 */
public final class ObjectProcessor {
    /**
     * 两对象间的域赋值（域名严格一致，包括大小写）
     * <p><em>注意：涉及到的类要有getter和setter</em></p>
     * @param originObj 赋值的对象
     * @param targetObj 待赋值的对象
     */
    public static void copyFields(@NonNull final Object originObj, @NonNull final Object targetObj){
        final Field[] targetFields = targetObj.getClass().getDeclaredFields();
        final Field[] originFields = originObj.getClass().getDeclaredFields();
        try {
            for (Field targetField : targetFields) {
                String targetFieldName = targetField.getName();
                for (Field originField : originFields) {
                    String originFieldName = originField.getName();
                    if(targetFieldName.equalsIgnoreCase(originFieldName)){
                        originField.setAccessible(true);
                        targetField.setAccessible(true);
                        targetField.set(targetObj, originField.get(originObj));
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}