package com.example.clientapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class LocalBinderActivity extends AppCompatActivity {


    private LocalBinder localBinder;

    private boolean mBound;

    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            localBinder = (LocalBinder)service;
            Log.i(MainActivity.TAG, "local binder:" + service);
            mBound = true;
        }

        public void onServiceDisconnected(ComponentName className) {
            localBinder = null;
            mBound = false;
        }
    };

    protected void say(View v) {
        if (localBinder != null) {
            localBinder.say();
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
        bindService(new Intent(this, LocalService.class), mConnection, Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(mConnection);
    }
}
