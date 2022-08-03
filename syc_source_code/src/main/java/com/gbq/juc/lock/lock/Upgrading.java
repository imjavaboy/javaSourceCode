package com.gbq.juc.lock.lock;


import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/2
 * @Copyright 总有一天，会见到成功
 */

public class Upgrading {
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);
    private static ReentrantReadWriteLock.ReadLock readLock =  reentrantReadWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock =  reentrantReadWriteLock.writeLock();

    /**
     *  可以降级
     * @param
     * @return
     */
    private static void writeLockDownGrading(){
        writeLock.lock();

        try {
            System.out.println(Thread.currentThread().getName()+":获取到了写锁,正在写入");
            Thread.sleep(1000);
            readLock.lock();
            System.out.println("在不释放写锁的情况下，成功降级");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
            System.out.println(Thread.currentThread().getName()+"释放写锁");
            writeLock.unlock();
        }

    }
    private static void readLockUoGrading(){
        readLock.lock();

        try {
            System.out.println(Thread.currentThread().getName()+":得到了读锁，正在读取");
            Thread.sleep(1000);
            System.out.println("锁升级");
            writeLock.lock();
            System.out.println(Thread.currentThread().getName()+":获取到了写锁，升级成功");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
            System.out.println(Thread.currentThread().getName()+"：释放写锁");
            readLock.unlock();
        }

    }


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->writeLockDownGrading(),"thread1");
        thread.start();
        thread.join();
        Thread thread1 = new Thread(()->readLockUoGrading(),"thread2");
        thread1.start();
    }
}
