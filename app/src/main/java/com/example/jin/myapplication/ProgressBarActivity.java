package com.example.jin.myapplication;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

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
            }
        });

    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            progressBar.setProgress(msg.arg1);
            handler.post(updateThread);
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

            handler.sendMessage(msg);
            if (i == 100) {
                handler.removeCallbacks(updateThread);
            }
        }
    };

}
