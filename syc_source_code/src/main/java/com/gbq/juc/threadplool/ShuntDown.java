package com.gbq.juc.threadplool;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 郭本琪
 * @description 关闭线程池
 * @date 2022/7/31
 * @Copyright 总有一天，会见到成功
 */

public class ShuntDown {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new ShuntDownTask());
        }
        System.out.println(executorService.isShutdown());
        Thread.sleep(1500);
        executorService.shutdown();
        System.out.println(executorService.isShutdown());
        System.out.println(executorService.isTerminated());
        Thread.sleep(10000);
        System.out.println(executorService.isTerminated());
//        executorService.execute(new ShuntDownTask());
    }
}
class ShuntDownTask implements Runnable{

    @Override
    public void run() {

        try {
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
