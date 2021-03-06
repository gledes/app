package com.example.clientapp.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.clientapp.MainActivity;
import com.example.clientapp.R;
import com.example.jin.myapplication.MyAidlInterface;

public class MyAidlActivity extends AppCompatActivity {


    private MyAidlInterface myAidlInterface;

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(MainActivity.TAG, "onServiceConnected");
            myAidlInterface = MyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            myAidlInterface = null;
            Log.d(MainActivity.TAG, "onServiceDisconnected");

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_aidl);

    }


    public void test(View view) {

        try {
            Toast.makeText(this, myAidlInterface.getName(), Toast.LENGTH_SHORT).show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public void bindService(View view) {

        Intent intent = new Intent("cc.abto.server");
        intent.setPackage("com.example.jin.myapplication");
//        intent.setComponent(new ComponentName("com.example.jin.myapplication", "com.mark.aidl.RemoteService"));
//        ComponentName componentName = new ComponentName("com.example.jin.myapplication", "com.example.jin.myapplication.service.aidlservice.MyService");
//        intent.setComponent(componentName);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);

    }

    public void unbindService(View view) {
        Log.d(MainActivity.TAG, "unbindService");
        unbindService(conn);
    }
}
