package com.hmy.gkrpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.awt.datatransfer.DataTransferer;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
    表示服务
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDescriptor {
    private String clazz;
    private String method;
    private String returnType;
    private String[] parameterTypes;

    public static <T> ServiceDescriptor from(Class clazz, Method method) {
        ReentrantLock
        ServiceDescriptor sdp = new ServiceDescriptor();
        sdp.setClazz(clazz.getName());
        sdp.setMethod(method.getName());
        sdp.setReturnType(method.getReturnType().getName());

        Class<?>[] parameterClasses = method.getParameterTypes();
        String[] parameterTypes =  new String[parameterClasses.length];
        for (int i = 0; i < parameterClasses.length; i++) {
            parameterTypes[i] = parameterClasses[i].getName();
        }
        sdp.setParameterTypes(parameterTypes);
        return sdp;
    }


    @Override
    public String toString() {
        return "ServiceDescriptor{" +
                "clazz='" + clazz + '\'' +
                ", method='" + method + '\'' +
                ", returnType='" + returnType + '\'' +
                ", parameterTypes=" + Arrays.toString(parameterTypes) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceDescriptor that = (ServiceDescriptor) o;

        if (clazz != null ? !clazz.equals(that.clazz) : that.clazz != null) return false;
        if (method != null ? !method.equals(that.method) : that.method != null) return false;
        if (returnType != null ? !returnType.equals(that.returnType) : that.returnType != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(parameterTypes, that.parameterTypes);
    }

    @Override
    public int hashCode() {
        int result = clazz != null ? clazz.hashCode() : 0;
        result = 31 * result + (method != null ? method.hashCode() : 0);
        result = 31 * result + (returnType != null ? returnType.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(parameterTypes);
        return result;
    }
}
