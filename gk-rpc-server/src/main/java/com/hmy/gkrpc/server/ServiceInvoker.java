package com.hmy.gkrpc.server;

import com.hmy.gkrpc.Request;
import com.hmy.gkrpc.common.utils.ReflectionUtils;
import com.hmy.transport.RequestHandler;

/**
 * @Description 调用具体服务
 */
public class ServiceInvoker {
    public Object invoke(ServiceInstance service, Request request){
        return ReflectionUtils.invoke(service.getTarget(), service.getMethod(), request.getParams());
    }
}
