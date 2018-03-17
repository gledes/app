package com.example.clientapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by jin on 2018/3/17.
 */

public class LocalService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(getApplicationContext(), "local binding", Toast.LENGTH_SHORT).show();
        return new LocalBinder();
    }
}
