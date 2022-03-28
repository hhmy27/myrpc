package com.hmy.gkrpc.common.utils;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description 反射工具类
 */
public class ReflectionUtils {
    /**
     * @param clazz 待创建对象的类
     * @param <T>   对象类型
     * @return 创建好的对象
     * @Description 根据class创建对象
     */
    public static <T> T newInstance(Class<T> clazz) {
        T instance = null;
        try {
            instance = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    /**
     * @Description 获取某个class的public方法
     * @param clazz
     * @return 当前类声明的public方法
     */
    public static Method[] getPublicMethods(Class clazz) {
        List<Method> pMethods = new ArrayList<>();
        // 获取所有方法
        Method[] methods = clazz.getDeclaredMethods();
        // 过滤出public方法
        pMethods = Arrays.stream(methods).filter(method ->
                Modifier.isPublic(method.getModifiers())).
                collect(Collectors.toList());
        return pMethods.toArray(new Method[0]);
    }


    /**
     * 调用对象的指定方法
     * @param obj 被调用方法的对象
     * @param method 被调用的方法
     * @param args 方法的参数
     * @return object 返回结果
     */
    public static Object invoke(Object obj, Method method, Object... args){

        Object object = null;
        try {
            object = method.invoke(obj, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return object;
    }
}
