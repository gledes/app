package com.example.jin.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private Button startButton;

    private Button endButton;

    private Button progress;

    private Button handleThreadTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button)findViewById(R.id.startButtn);
        endButton = (Button)findViewById(R.id.endBubttn);

        startButton.setOnClickListener(new StartButtonListener());
        endButton.setOnClickListener(new EndButtonListener());

        progress = (Button) findViewById(R.id.progress);

        progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ProgressBarActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        handleThreadTest = (Button)findViewById(R.id.handerThreadTest);
//        handleThreadTest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(MainActivity.this, HanderThreadTest.class);
//            }
//        });



    }

    class StartButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            handler.post(updateThread);
        }
    }

    class EndButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            handler.removeCallbacks(updateThread);
        }
    }

    Handler handler = new Handler();
    Runnable updateThread = new Runnable() {
        @Override
        public void run() {
            System.out.print("UpdateThread");
            Log.i("UpdateThread", "UpdateThread");
            handler.postDelayed(updateThread, 3000);
        }
    };
}
