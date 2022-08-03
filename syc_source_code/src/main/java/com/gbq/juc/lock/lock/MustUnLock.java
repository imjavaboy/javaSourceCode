package com.gbq.juc.lock.lock;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 郭本琪
 * @description 不会手动释放死哦
 * @date 2022/8/1
 * @Copyright 总有一天，会见到成功
 */

public class MustUnLock {
    private static Lock lock = new ReentrantLock();
    public static void main(String[] args) {
        lock.lock();

        try {
            System.out.println(Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
