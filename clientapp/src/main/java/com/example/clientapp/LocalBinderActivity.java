package com.example.clientapp;

import android.app.Notification;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.BitmapDrawable;
import android.os.IBinder;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.clientapp.notification.NotificationUtils;

public class LocalBinderActivity extends AppCompatActivity {

    private LocalService localService;

    private LocalService.LocalBinder localBinder;

    private boolean mIsBind = false;

    private boolean mIsConnected = false;

    private boolean mIsForegroundService = false;

    private final int NOTIFICATION_ID = 98;

    private ServiceConnection mConnection = new ServiceConnection() {
            public void onServiceConnected(ComponentName className, IBinder service) {
            localBinder = (LocalService.LocalBinder)service;
            localService = localBinder.getService();
            Log.i(MainActivity.TAG, "local binder:" + service);
            mIsConnected = true;
        }

        public void onServiceDisconnected(ComponentName className) {
            localBinder = null;
            mIsConnected = false;
        }
    };

    protected void say(View v) {
        if (localService != null) {
            Toast.makeText(LocalBinderActivity.this, localService.getRandomNumber() + "", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_binder);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mIsBind = bindService(new Intent(this, LocalService.class), mConnection, Context.BIND_AUTO_CREATE);

    }

    protected void onResume() {
        super.onResume();
        if (mIsConnected && mIsForegroundService) {
            localService.stopForeground(true);
            mIsForegroundService = false;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
//        unbindService(mConnection);
        if (mIsBind && localService!=null && !mIsForegroundService) {
            localService.startForeground(NOTIFICATION_ID, getNotification());
            mIsForegroundService = true;
        }

    }

//    private Notification getNotification() {
//        Notification.Builder builder = new Notification.Builder(LocalBinderActivity.this);
//        builder.setShowWhen(false);
//        builder.setAutoCancel(false);
//        builder.setSmallIcon(R.mipmap.ic_launcher_round);
////        builder.setLargeIcon(((BitmapDrawable)getDrawable(R.mipmap.ic_launcher)).getBitmap());
//        builder.setContentText("this is content text");
//        builder.setContentTitle("this is title");
//        return builder.build();
//    }

    private Notification getNotification() {
        NotificationUtils notificationUtils = new NotificationUtils(this);
        return notificationUtils.getNotification("测试标题", "测试内容");
    }
}
