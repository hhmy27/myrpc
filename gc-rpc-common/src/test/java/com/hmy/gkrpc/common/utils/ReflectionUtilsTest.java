package com.hmy.gkrpc.common.utils;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ReflectionUtilsTest {

    @Test
    public void newInstance() {
        TestClass testClass = ReflectionUtils.newInstance(TestClass.class);
        assertNotNull(testClass);
    }

    @Test
    public void getPublicMethods() {
        Method[] methods = ReflectionUtils.getPublicMethods(TestClass.class);
        System.out.println(Arrays.asList(methods));
        assertEquals(1, methods.length);

        String name = methods[0].getName();
        assertEquals("B", name);
    }

    @Test
    public void invoke() {
        Class clazz = TestClass.class;
        Object obj = ReflectionUtils.newInstance(clazz);
        Method bMethod = ReflectionUtils.getPublicMethods(clazz)[0];
        String result = (String) ReflectionUtils.invoke(obj, bMethod);
        assertEquals("B", result);

    }
}