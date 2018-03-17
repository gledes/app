package com.example.clientapp;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by jin on 2018/3/17.
 */

public class LocalService extends Service {

    private IBinder binder = new LocalBinder();

    private final Random mGenerator = new Random();


    public class LocalBinder extends Binder {

        public LocalService getService() {
            return LocalService.this;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(getApplicationContext(), "local binding", Toast.LENGTH_SHORT).show();
        return binder;
    }

    public int getRandomNumber() {
        return mGenerator.nextInt(100);
    }


}
