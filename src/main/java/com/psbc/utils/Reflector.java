package com.psbc.utils;

import com.psbc.pojo.TableModel;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import com.psbc.utils.random.Random;

public class Reflector {

    /**
     * 根据类名得到一个对应的默认构造函数的对象
     */
    public static TableModel getFileObjectFrom(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        final String classPath = "./src/main/java/com/psbc/pojo/";
        final String packageName = "com.psbc.pojo.";
        File[] files = new File(classPath).listFiles();
        if (files != null && files.length > 0) {
            for (File f : files) {
                if (f.getName().equals(className + ".java")) {
                    Class<?> clazz = Class.forName(packageName + className);
                    return (TableModel) clazz.newInstance();
                }
            }
        }
        throw new ClassNotFoundException();
    }

    /**
     *
     * @param clazz
     * @param fieldName
     * @return
     */
    public static Object invokeGetMethod(Object clazz, String fieldName) {
        String methodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        try {
            Method method = Class.forName(clazz.getClass().getName()).getDeclaredMethod("get" + methodName);
            return method.invoke(clazz);
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据由随机配置文件中的randomType(类名)，返回该类的一个空参对象
     */
    public static Random getRandomInstanceFrom(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        final String classPath = "./src/main/java/com/psbc/utils/random/";
        final String packageName = "com.psbc.utils.random.";

        File[] files = new File(classPath).listFiles();

        if(files.length > 0){
            for(File f : files){
                if(f.getName().equals(className + ".java")) {
                    Class<?> clazz = Class.forName(packageName + className);
                    return (Random) clazz.newInstance();
                }
            }
        }
        throw new ClassNotFoundException();
    }
}