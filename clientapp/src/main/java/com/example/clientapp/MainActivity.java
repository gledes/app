package com.example.clientapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mark.aidl.AidlRemote;
import com.mark.aidl.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public final static String TAG = MainActivity.class.getSimpleName();



    protected void aidl(View v) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, AidlActivity.class);
        startActivity(intent);
    }

    protected void onMessenger(View v) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, MessengerActivity.class);
        startActivity(intent);
    }

    protected void onMessengerClient(View v) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, MessengerClientActivity.class);
        startActivity(intent);
    }


    protected void localBinderTest(View v) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, LocalBinderActivity.class);
        startActivity(intent);
    }

    void intentService(View v) {
        Intent intent = new Intent();
        intent.setClass(this, IntentServiceActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences.Editor sharedata = getSharedPreferences("data", 0).edit();



    }







    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}