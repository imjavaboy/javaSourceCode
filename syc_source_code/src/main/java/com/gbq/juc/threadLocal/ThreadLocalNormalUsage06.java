package com.gbq.juc.threadLocal;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/1
 * @Copyright 总有一天，会见到成功
 */

public class ThreadLocalNormalUsage06 {
    public static void main(String[] args) {
        new Service1().process("");
    }
}
class Service1 {

    public void process(String name) {
        User user = new User("超哥");
        UserContextHolder.holder.set(user);
        new Service2().process();
    }
}

class Service2 {

    public void process() {
        User user = UserContextHolder.holder.get();
        ThreadLocalFormatter.dateFormatThreadLocal.get();
        System.out.println("Service2拿到用户名：" + user.name);
        new Service3().process();
    }
}

class Service3 {

    public void process() {
        User user = UserContextHolder.holder.get();
        System.out.println("Service3拿到用户名：" + user.name);
        UserContextHolder.holder.remove();
    }
}
class UserContextHolder {

    public static ThreadLocal<User> holder = new ThreadLocal<>();


}
class User {

    String name;
    public User(String name) {
        this.name = name;
    }
}
