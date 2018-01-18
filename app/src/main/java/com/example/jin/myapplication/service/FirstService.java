package com.example.jin.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.jin.myapplication.MainActivity;

public class FirstService extends Service {


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        Log.i(MainActivity.tag, "on bind");
        return null;
    }

    public void onCreate() {
        super.onCreate();
        Log.i(MainActivity.tag, "on create");
    }

    public int onStartCommand(Intent intent,  int flags, int startId){

        Log.d(MainActivity.tag, "flags:" + flags);
        Log.d(MainActivity.tag, "startId:" + startId);
        Log.d(MainActivity.tag, "service onStartCommand");
        return START_NOT_STICKY;
    }

    public void onDestroy() {
        Log.i(MainActivity.tag, "on destroy");
        super.onDestroy();
    }


}
