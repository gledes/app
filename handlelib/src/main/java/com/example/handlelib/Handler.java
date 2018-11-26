package com.example.handlelib;

public class Handler {

    MessageQueue queue;

    Looper looper;

    public Handler() {
        looper = Looper.myLooper();
        queue = looper.queue;
    }

    public void handleMessage(Message msg) {

    }

    public void sendMessage(Message msg) {
        msg.target = this;
        queue.add(msg);
    }

}
