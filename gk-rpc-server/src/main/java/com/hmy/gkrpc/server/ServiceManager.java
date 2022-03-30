package com.hmy.gkrpc.server;

import com.hmy.gkrpc.Request;
import com.hmy.gkrpc.ServiceDescriptor;
import com.hmy.gkrpc.common.utils.ReflectionUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description 管理所有服务
 */
@Data
@Slf4j
public class ServiceManager {
    private final ConcurrentHashMap<ServiceDescriptor, ServiceInstance> services;

    public ServiceManager() {
        this.services = new ConcurrentHashMap<>();
    }

    // 注册服务
    public <T> void register(Class<T> interfaceClass, T bean) {
        Method[] methods = ReflectionUtils.getPublicMethods(interfaceClass);
        for (Method method : methods) {
            ServiceInstance serviceInstance = new ServiceInstance(bean, method);
            ServiceDescriptor sdp = ServiceDescriptor.from(interfaceClass, method);
            services.put(sdp, serviceInstance);
            log.info("register service: {}:{}", sdp.getClazz(), sdp.getMethod());
        }
    }

    // 查找服务
    public ServiceInstance lookup(Request request) {
        return services.get(request.getServiceDescriptor());

    }
}
