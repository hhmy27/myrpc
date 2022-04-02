package com.hmy.gkrpc.example;

import com.hmy.gkrpc.client.RpcClient;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 */
public class Client {
    public static void main(String[] args) {
        RpcClient client = new RpcClient();
        CalcService service = client.getProxy(CalcService.class);
        int result = service.add(1, 2);
        System.out.println(result);
        PrintService printService = client.getProxy(PrintService.class);
        System.out.println(printService.print("i just want to sleep"));
//        System.out.println(result);
//        System.out.println(service.minus(1, 10));
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

    }
}
