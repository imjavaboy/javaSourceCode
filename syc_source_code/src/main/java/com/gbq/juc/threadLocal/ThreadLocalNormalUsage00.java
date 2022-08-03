package com.gbq.juc.threadLocal;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/1
 * @Copyright 总有一天，会见到成功
 */

public class ThreadLocalNormalUsage00 {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(()->{
                String date = new ThreadLocalNormalUsage00().date(finalI *100);
                System.out.println(date);
            }).start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }


    public String date(int seconds){
        Date date = new Date(1000*seconds);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }
}
