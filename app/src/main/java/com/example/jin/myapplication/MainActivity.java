package com.example.jin.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.jin.myapplication.broadcast.BroadcastActivity;
import com.example.jin.myapplication.contentprovider.ContentProviderActivity;
import com.example.jin.myapplication.floatwindow.GetFloatWindowDirectlyActivity;
import com.example.jin.myapplication.hadler.HandlerThread2Activity;
import com.example.jin.myapplication.notification.NotificationUtils;
import com.example.jin.myapplication.service.ServiceActivity;
import com.example.jin.myapplication.service.intentservice.IntentServiceActivity;
import com.example.jin.myapplication.sqlite.SQLiteActivity;
import com.example.jin.myapplication.webviewfiledemo.WebViewActivity;

public class MainActivity extends Activity {

    public static final String tag = "MyApp";

    private Button startButton;

    private Button endButton;

    private Button progress;

    private Button handleThreadTest;

    private Button sqliteTest;

    private Button contentProviderTest;

    private Button broadcast;

    private Button service;

    private Button webviewTest;

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

        broadcast = (Button)findViewById(R.id.broadcast);
        broadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, BroadcastActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        service = (Button)findViewById(R.id.service);
        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ServiceActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        webviewTest = (Button)findViewById(R.id.webviewTest);
        webviewTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, WebViewActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }

    void intentService(View v) {
        Intent intent = new Intent();
        intent.setClass(this, IntentServiceActivity.class);
        startActivity(intent);
    }

    void hadlerThread2(View v) {
        Intent intent = new Intent();
        intent.setClass(this, HandlerThread2Activity.class);
        startActivity(intent);
    }

    void floatWindow(View v) {
        Intent intent = new Intent();
        intent.setClass(this, GetFloatWindowDirectlyActivity.class);
        startActivity(intent);
    }

    public void Notification(View view) {
        NotificationUtils notificationUtils = new NotificationUtils(this);
        notificationUtils.sendNotification("测试标题", "测试内容");
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
