package com.gbq.juc.threadplool;


/**
 * @author 郭本琪
 * @description
 * @date 2022/7/30
 * @Copyright 总有一天，会见到成功
 */

public class EveryTaskOneThread {

    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println("hello....");
        new Thread(runnable).start();

    }
}
