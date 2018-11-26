package com.example.handlelib;

public class Looper {

    static ThreadLocal<Looper> threadLocal = new ThreadLocal<Looper>();

    MessageQueue queue;

    private Looper() {
        queue = new MessageQueue();
    }

    public static void prepare() {

        if (threadLocal.get() != null) {
            throw new RuntimeException("Looper被创建过了");
        }
        threadLocal.set(new Looper());
    }

    public static Looper myLooper() {
        return threadLocal.get();
    }

    public static void loop() {
        Looper looper = myLooper();
        MessageQueue mq = looper.queue;
        for (; ; ) {
            Message msg = mq.next();
//            System.out.println("msg:" + msg.obj.toString());
            msg.target.handleMessage(msg);
        }
    }

}
