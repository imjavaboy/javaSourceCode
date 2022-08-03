package com.gbq.juc.atomic;


import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/2
 * @Copyright 总有一天，会见到成功
 */

public class AtomicArrayDemo {
    public static void main(String[] args) {
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10000);
        Incrementer incrementer = new Incrementer(atomicIntegerArray);
        Decrementer decrementer = new Decrementer(atomicIntegerArray);
        Thread[] threads1 = new Thread[1000];
        Thread[] threads2 = new Thread[1000];
        for (int i = 0; i < 1000; i++) {
            threads1[i] = new Thread(incrementer);
            threads2[i] = new Thread(decrementer);
            threads1[i].start();
            threads2[i].start();
        }
        for (int i = 0; i < 1000; i++) {
            try {
                threads1[i].join();
                threads2[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            System.out.println(atomicIntegerArray.get(i));

        }
    }

}
class Decrementer implements Runnable{
    private AtomicIntegerArray array;
    public Decrementer(AtomicIntegerArray array){
        this.array = array;
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length(); i++) {
            array.getAndDecrement(i);
        }
    }
}
class Incrementer implements Runnable{
    private AtomicIntegerArray array;
    public Incrementer(AtomicIntegerArray array){
        this.array = array;
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length(); i++) {
            array.getAndIncrement(i);
        }
    }
}
