package com.psbc.business.service;

import lombok.NonNull;

import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * 对象处理器
 */
public final class ObjectProcessor {


    /**
     * 两对象间的域赋值
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
                    originField.setAccessible(true);
                    targetField.setAccessible(true);
                    if(targetFieldName.equalsIgnoreCase(originFieldName) && originField.get(originObj) != null && originField.get(originObj).toString().trim().length()>0){
                        String type = targetField.getType().getSimpleName();

                        switch (type) {
                            case "Integer":
                                targetField.set(targetObj, Integer.parseInt(originField.get(originObj).toString()));
                                break;
                            case "String":
                                targetField.set(targetObj, originField.get(originObj).toString());
                                break;
                            case "BigDecimal":
                                targetField.set(targetObj, new BigDecimal(originField.get(originObj).toString()));
                                break;
                            case "Double":
                                targetField.set(targetObj, Double.parseDouble(originField.get(originObj).toString()));
                                break;
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}