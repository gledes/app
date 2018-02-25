package com.example.jin.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

/**
 * ProgressBar 13集
 * Handler 14 15集
 */
public class ProgressBarActivity extends Activity {

    private Button startButton;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);

        startButton = (Button)findViewById(R.id.startBut);

        progressBar = (ProgressBar) findViewById(R.id.bar);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                handler.post(updateThread);
                Log.i(MainActivity.tag, "main:" + Thread.currentThread().getName());
            }
        });

    }

    public void test(View v) {
        Log.i(MainActivity.tag, "test:" + Thread.currentThread().getName());
    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            progressBar.setProgress(msg.arg1);
            handler.post(updateThread);
            Log.i(MainActivity.tag, "HandleMessage:" + Thread.currentThread().getName());
        }
    };

    Runnable updateThread = new Runnable() {
        int i = 0;
        @Override
        public void run() {
            i = i + 10;
            Message msg = handler.obtainMessage();
            msg.arg1 = i;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Log.i(MainActivity.tag, "run:" + Thread.currentThread().getName());
            if (i <= 100) {
                handler.sendMessage(msg);
            } else {
                handler.removeCallbacks(updateThread);
            }
        }
    };

}
