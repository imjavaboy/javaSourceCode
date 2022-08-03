package com.gbq.juc.atomic;


import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/2
 * @Copyright 总有一天，会见到成功
 */

public class AtomicIntegerFieldUpdaterDemo implements Runnable{
    static Candidate tom;
    static Candidate peter;

    public static AtomicIntegerFieldUpdater<Candidate> scoreUpdater =
            AtomicIntegerFieldUpdater.newUpdater(Candidate.class,"score");

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            peter.score++;
            scoreUpdater.getAndIncrement(tom);
        }
    }


    private static class Candidate{
        volatile int score;
    }

    public static void main(String[] args) {
        try {
            tom = new Candidate();
            peter = new Candidate();
            AtomicIntegerFieldUpdaterDemo updaterDemo = new AtomicIntegerFieldUpdaterDemo();
            Thread t1 = new Thread(updaterDemo);
            Thread t2 = new Thread(updaterDemo);
            t1.start();
            t2.start();
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("普通"+peter.score);
            System.out.println("原子"+tom.score);
        }
    }
}
