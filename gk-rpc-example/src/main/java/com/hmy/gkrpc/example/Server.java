package com.hmy.gkrpc.example;

import com.hmy.gkrpc.ServiceDescriptor;
import com.hmy.gkrpc.server.RpcServer;
import com.hmy.gkrpc.server.ServiceManager;

/**
 * @Description
 */
public class Server {
    public static void main(String[] args) {
        RpcServer server = new RpcServer();
        server.register(CalcService.class, new CalcServiceImpl());
        server.register(PrintService.class, new PrintServiceImpl());
        System.out.println("registered service manager:");
        ServiceManager serviceManager = server.getServiceManager();
        for (ServiceDescriptor serviceDescriptor : serviceManager.getServices().keySet()) {
            System.out.println(serviceDescriptor.getClazz() + " : " + serviceDescriptor.getMethod());
        }
//        System.out.println(server.getServiceManager());
        server.start();
    }
}
