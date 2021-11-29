package com.psbc.business.service;

import com.psbc.TaMockProjectApplication;
import com.psbc.mapper.AccountApplicationDao;
import com.psbc.pojo.TableModel;
import com.psbc.reader.xmlModel.XMLNode;
import com.psbc.utils.StringProcessor;
import com.psbc.utils.helper.XMLParser;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

@Data
@Component
public class RecordOperator {

    private Object targetObject;

    private String businessCode;
    private String businessXmlPath = ".\\src\\main\\resources\\xml\\business_configs\\";
    private TableModel tableModel;
    private boolean checkerResult = true;
    private static final Logger logger = Logger.getLogger(TaMockProjectApplication.class);

    public boolean checkerFiledValue(TableModel tableModel) {

        this.businessXmlPath = ".\\src\\main\\resources\\xml\\business_configs\\"+this.businessCode+".xml";

        XMLNode attrNode = XMLParser.parseXml(this.businessXmlPath);
        List<XMLNode> childrenNodes = attrNode.getChildrenNodes();
        for (XMLNode x : childrenNodes
        ) {
            Map<String, String> attributes = x.getAttributes();
            Set<String> strings = attributes.keySet();

            String fieldName = "";
            String fieldNameValue = "";
            String length = "";
            for (String k : strings
            ) {
                if (k.equals("fieldName")) {
                    Field field = null;
                    fieldName = attributes.get(k);
                    try {
                        field = tableModel.getClass().getDeclaredField(fieldName.toLowerCase());
                        field.setAccessible(true);
                        Object o = field.get(tableModel);
                        fieldNameValue = o.toString();

                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

                if (k.equals("length")) {
                    length = attributes.get(k);
                }
            }
            if (!(StringProcessor.cnLength(fieldNameValue) <= Integer.valueOf(length))) {
                System.out.println(fieldNameValue + " " + length);
                this.checkerResult = false;
            }
        }


        return this.checkerResult;


    }

    public static Object invokeGetMethod(Object clazz, String fieldName, Object[] args) {
        String methodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        Method method = null;
        try {
            method = Class.forName(clazz.getClass().getName()).getDeclaredMethod("get" + methodName);
            return method.invoke(clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public  Object getTargetObject(Object sourceObject, Object targetObject) {
        Object object = this.objectToObject(sourceObject, targetObject);
        return object;
    }

    public Object getBeanUtilsCopyProperties(Object sourceObject, Object targetObject) {
        BeanUtils.copyProperties(sourceObject,targetObject);
        return targetObject;
    }




    private Object objectToObject(Object sourceObject, Object targetObject) {

//        List<Field> tableModelDeclaredFields = Arrays.asList(sourceObject.getClass().getDeclaredFields());
//        List<Field> databaseModelDeclaredFields = Arrays.asList(targetObject.getClass().getDeclaredFields());

        List<Field> tableModelDeclaredFields = Arrays.asList(FieldUtils.getAllFields(sourceObject.getClass()));

        List<Field> databaseModelDeclaredFields = Arrays.asList(FieldUtils.getAllFields(targetObject.getClass()));

        for (Field a : tableModelDeclaredFields
        ) {
            Object value = invokeGetMethod(sourceObject, a.getName(), null);
            for (Field c : databaseModelDeclaredFields
            ) {
                c.setAccessible(true);
                String lowa = a.getName().toLowerCase();
                String lowc = c.getName().toLowerCase();

                if (value == null) {
                    break;
                }
                if (lowa.equals(lowc)) {
                    try {
                        if (c.getType().getSimpleName().equals("String")) {
                            c.set(targetObject, value.toString());
                        }
                        if (c.getType().getSimpleName().equals("Double")) {
                            c.set(targetObject, Double.valueOf(value.toString()));
                        }
                        if (c.getType().getSimpleName().equals("Integer")) {
                            c.set(targetObject, Integer.valueOf(value.toString()));
                        }
                        if (c.getType().getSimpleName().equals("BigDecimal")) {
                            c.set(targetObject, BigDecimal.valueOf(Long.parseLong(value.toString())));
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


        this.targetObject = targetObject;

        return this.targetObject;

    }



    /**
     * 忽略大小写转换bean类型
     *
     * @param obj 转换的源对象
     * @param clz 目标对象
     * @return 转换后的对象
     */
    public static <T> T transferObjectIgnoreCase(Object obj, Class clz) {
        T result = null;
        try {
            if (obj != null && !obj.equals("")) {
                result = (T) clz.newInstance();
                //获取目标类的属性集合
                Map<String, Field> destPropertyMap = new HashMap<>();

                for (Field curField : clz.getDeclaredFields()) {
                    destPropertyMap.put(curField.getName().toLowerCase(), curField);
                }
                //拷贝属性
//                obj.getClass().getDeclaredFields()
                for (Field curField : Arrays.asList(FieldUtils.getAllFields(obj.getClass()))) {
                    Field targetField = destPropertyMap.get(curField.getName().toLowerCase());
                    if (targetField != null) {
                        targetField.setAccessible(true);
                        curField.setAccessible(true);
                        targetField.set(result, curField.get(obj));
                    }
                }
            }
        } catch (Exception e1) {

            logger.info(e1);
        }
        return result;
    }




}
