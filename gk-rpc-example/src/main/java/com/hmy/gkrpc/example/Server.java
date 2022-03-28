package com.hmy.gkrpc.example;

import com.hmy.gkrpc.server.RpcServer;

/**
 * @Description
 */
public class Server {
    public static void main(String[] args) {
        RpcServer server = new RpcServer();
        server.register(CalcService.class, new CalcServiceImpl());
        server.start();
    }
}
