package com.hmy.gkrpc.example;

import com.hmy.gkrpc.client.RpcClient;

/**
 * @Description
 */
public class Client {
    public static void main(String[] args) {
        RpcClient client = new RpcClient();
        CalcService service = client.getProxy(CalcService.class);
        int result = service.add(1, 2);

        System.out.println(result);
        System.out.println(service.minus(1, 10));
    }
}
