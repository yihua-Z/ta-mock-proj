package com.psbc.business.service;

import com.psbc.mapper.AccountApplicationMapper;
import com.psbc.pojo.AccountApplication;
import com.psbc.pojo.TableModel;
import com.psbc.reader.xmlModel.XMLNode;
import com.psbc.utils.StringProcessor;
import com.psbc.utils.helper.XMLParser;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

@Data
@Component
public class RecordOperator {

    @Autowired
    AccountApplicationMapper accountApplicationMapper;
    private Object targetObject;

    private String businessCode;
    private String businessXmlPath = ".\\src\\main\\resources\\xml\\business_configs\\";
    private TableModel tableModel;
    private boolean checkerResult = true;

    public boolean checkerFiledValue(TableModel tableModel) {

        this.businessXmlPath += this.businessCode + ".xml";

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
                        field = tableModel.getClass().getDeclaredField(fieldName);
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

    public Object invokeGetMethod(Object clazz, String fieldName, Object[] args) {
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

    public Object getTargetObject(Object sourceObject, Object targetObject) {
        Object object = this.objectToObject(sourceObject, targetObject);
        return object;
    }

    private Object objectToObject(Object sourceObject, Object targetObject) {
        HashMap<String, String[]> stringHashMap = null;

        List<Field> tableModelDeclaredFields = Arrays.asList(sourceObject.getClass().getDeclaredFields());
        List<Field> databaseModelDeclaredFields = Arrays.asList(targetObject.getClass().getDeclaredFields());

        for (Field a : tableModelDeclaredFields
        ) {
            Object value = invokeGetMethod(sourceObject, a.getName(), null);
            for (Field c : databaseModelDeclaredFields
            ) {
                c.setAccessible(true);
                if (a.getName().equals(c.getName())) {
                    try {
                        if (c.getType().getSimpleName().equals("String")) {
                            c.set(targetObject, value.toString());
                        }
                        if (c.getType().getSimpleName().equals("Double")) {
                            c.set(targetObject, Double.valueOf(value.toString()));
                        }


                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        for (Field c : databaseModelDeclaredFields) {
            Object o = null;
            try {
                c.setAccessible(true);
                o = c.get(targetObject);
                if (o == null) {
                    c.set(targetObject, "");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        this.targetObject = targetObject;
        //        记录入库(所有字段到account_application
        //        读取“account_expectation”, 根据主键判断是否有其对应的期望的确认条目


        return this.targetObject;

    }
}
