package com.example.jin.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class HadlerThreadActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hander_thread);
        HandlerThread handlerThread = new HandlerThread("handler_thread");
        handlerThread.start();

        MyHandler myHandler = new MyHandler(handlerThread.getLooper());
        Message msg = myHandler.obtainMessage();
        msg.sendToTarget();
        Log.i(MainActivity.tag, Thread.currentThread().getName() + ":" + Thread.currentThread().getId());
    }

    class MyHandler extends Handler {
        public MyHandler() {
            super();
        }

        public MyHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message msg) {
            Log.i(MainActivity.tag, "handleMessage");
            Log.i(MainActivity.tag, Thread.currentThread().getName() + ":" + Thread.currentThread().getId());
        }

    }
}
