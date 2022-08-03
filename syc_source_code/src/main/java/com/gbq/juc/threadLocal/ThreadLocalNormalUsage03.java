package com.gbq.juc.threadLocal;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 郭本琪
 * @description ThreadLocal
 * @date 2022/8/1
 * @Copyright 总有一天，会见到成功
 */

public class ThreadLocalNormalUsage03 {
    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.execute(()->{
                String date = new ThreadLocalNormalUsage03().date(finalI);
                System.out.println(date);
            });

        }


    }


    public String date(int seconds){
        Date date = new Date(1000*seconds);
//        String format = dateFormat.format(date);
        SimpleDateFormat dateFormat = ThreadLocalFormatter.dateFormatThreadLocal.get();
        return dateFormat.format(date);
    }
}
class ThreadLocalFormatter{
    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
}
