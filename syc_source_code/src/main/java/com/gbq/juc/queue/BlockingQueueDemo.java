package com.gbq.juc.queue;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author 郭本琪
 * @description 模拟生产者消费者
 * @date 2022/8/2
 * @Copyright 总有一天，会见到成功
 */

public class BlockingQueueDemo {
    public static void main(String[] args) {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
class Producer implements Runnable{
    BlockingQueue<String> queue;

    public Producer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("十个消息");
        for (int i = 0; i < 10; i++) {
            String msg = "消息信息"+i;

            try {
                queue.put(msg);
                System.out.println(msg+"-->消息已经放进去了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            queue.put("stop");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
class Consumer implements Runnable{
    BlockingQueue<String> queue;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String msg;

        try {
            while (!(msg = queue.take()).equals("stop")){
                System.out.println(msg+"--->取到了");
            }
            System.out.println("消息取出完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
