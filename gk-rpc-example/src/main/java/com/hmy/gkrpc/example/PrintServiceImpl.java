package com.hmy.gkrpc.example;

/**
 * @Description
 */
public class PrintServiceImpl implements PrintService {
    @Override
    public String print(String str) {
        return "service output: " + str;
    }
}
