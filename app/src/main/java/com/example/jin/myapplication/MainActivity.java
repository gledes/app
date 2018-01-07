package com.example.jin.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.jin.myapplication.contentprovider.ContentProviderActivity;
import com.example.jin.myapplication.sqlite.SQLiteActivity;

public class MainActivity extends Activity {

    public static final String tag = "MyApp";

    private Button startButton;

    private Button endButton;

    private Button progress;

    private Button handleThreadTest;

    private Button sqliteTest;

    private Button contentProviderTest;

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
        handleThreadTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, HadlerThreadActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        sqliteTest = (Button)findViewById(R.id.sqliteTest);
        sqliteTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SQLiteActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        contentProviderTest = (Button)findViewById(R.id.contentProvider);
        contentProviderTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ContentProviderActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
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
            Log.i(tag, "UpdateThread");
            handler.postDelayed(updateThread, 3000);
        }
    };
}
