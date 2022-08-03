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

public class CountDownLatchDemoAndLw {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(5);
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            final  int no = i+1;
            Runnable runnable = ()->{
                try {
                    Thread.sleep((long) (Math.random()* 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.println("No"+no+"已经准备好");
                    begin.await();
                    Thread.sleep((long) (Math.random()* 1000));
                    System.out.println("No"+no+"已经到达终点");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    end.countDown();
                }

            };
            service.submit(runnable);

        }
        Thread.sleep(10000);
        System.out.println("比赛开始");
        begin.countDown();
        end.await();
        System.out.println("所有人跑步结束");
    }
}
