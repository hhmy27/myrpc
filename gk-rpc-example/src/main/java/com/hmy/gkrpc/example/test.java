package com.hmy.gkrpc.example;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 */
public class test {
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 10, 1, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(finalI + ": running");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(finalI + ": ending");
                }
            });
        }


    }
}
