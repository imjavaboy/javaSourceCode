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

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            final int no = i+1;
            Runnable runnable = ()->{
                try {
                    Thread.sleep((long)(Math.random() * 10000 ));
                    System.out.println("NO"+(no) +"已经检查完成");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    latch.countDown();
                }

            };
            service.submit(runnable);
        }
        System.out.println("等待五人检查玩。。。。。。");
        latch.await();
        System.out.println("所有人都完成了工作，进入下一个环节");
        service.shutdown();
    }
}
