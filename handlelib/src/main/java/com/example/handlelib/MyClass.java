package com.example.handlelib;

public class MyClass {

    public static void main(String args[]) {
        System.out.println("你好");


        Looper.prepare();

        final Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                System.out.println("接收：" + msg.obj);
            }
        };

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Message msg = new Message();
                    msg.obj = "线程" + Thread.currentThread() + "发送数据";
                    handler.sendMessage(msg);
                }
            });
            thread.start();
        }

        Looper.loop();

    }
}
