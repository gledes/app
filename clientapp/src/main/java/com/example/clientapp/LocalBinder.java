package com.example.clientapp;

import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by jin on 2018/3/17.
 */

public class LocalBinder extends Binder {

    public void say() {
        Log.i(MainActivity.TAG, "hello");
    }
}
