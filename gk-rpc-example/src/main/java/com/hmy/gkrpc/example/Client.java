package com.hmy.gkrpc.example;

import com.hmy.gkrpc.client.RpcClient;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/**
 * @Description
 */
public class Client {
    public static void main(String[] args) {
        RpcClient client = new RpcClient();
        CalcService service = client.getProxy(CalcService.class);
        // 远程调用
        int result = service.add(1, 2);
        System.out.println(result);
    }
}
