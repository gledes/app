package com.example.jin.myapplication.service.aidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.jin.myapplication.MyAidlInterface;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        return new MyBinder();
    }

    class MyBinder extends MyAidlInterface.Stub {
        public String getName() throws RemoteException {
            return "test";
        }
    }

}
