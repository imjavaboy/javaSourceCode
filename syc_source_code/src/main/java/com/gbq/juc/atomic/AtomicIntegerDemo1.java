package com.gbq.juc.atomic;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/2
 * @Copyright 总有一天，会见到成功
 */

public class AtomicIntegerDemo1 implements Runnable{
    private  static AtomicInteger atomicInteger = new AtomicInteger();

    public void incrementAtomic(){
        atomicInteger.getAndAdd(1);
    }
    public static volatile int basicCount = 0;
    public  void incrementBasic() {
        basicCount++;
    }
    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerDemo1 run = new AtomicIntegerDemo1();
        Thread t1 = new Thread(run);
        t1.start();
        Thread t2 = new Thread(run);
        t2.start();
        t1.join();
        t2.join();
        System.out.println("原子"+atomicInteger.get());
        System.out.println("普通："+basicCount);
    }


    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            incrementAtomic();
            incrementBasic();
        }
    }
}
