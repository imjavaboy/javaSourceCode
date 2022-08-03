package com.gbq.juc.flowcontrol.countdownlatch;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/3
 * @Copyright 总有一天，会见到成功
 */

public class CountDownLatchDemo2 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            final  int no  = i+1;
            Thread.sleep((long)(Math.random()*1000));
            Runnable runnable = ()->{
                System.out.println("NO"+no+"准备完毕，等待跑步");

                try {
                    latch.await();
                    System.out.println("NO"+no+"开始跑步");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            service.submit(runnable);
        }
        Thread.sleep(10000);
        System.out.println("比赛开始");
        latch.countDown();
        service.shutdown();
    }
}
