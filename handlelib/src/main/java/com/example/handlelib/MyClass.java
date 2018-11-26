package com.example.handlelib;

public class MyClass {

    public static void main(String args[]) {
        System.out.println("���");


        Looper.prepare();

        final Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                System.out.println("���գ�" + msg.obj);
            }
        };

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Message msg = new Message();
                    msg.obj = "�߳�" + Thread.currentThread() + "��������";
                    handler.sendMessage(msg);
                }
            });
            thread.start();
        }

        Looper.loop();

    }
}
