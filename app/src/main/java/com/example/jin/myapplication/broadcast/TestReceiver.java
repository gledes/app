package com.example.jin.myapplication.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.jin.myapplication.MainActivity;

/**
 * Created by jin on 2018/1/7.
 */

public class TestReceiver extends BroadcastReceiver {

    public TestReceiver() {
        Log.i(MainActivity.tag, "test receiver");
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(MainActivity.tag, "onReceive");
    }
}
