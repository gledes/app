package com.example.clientapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.app.JinManager;

import com.example.clientapp.client.MyAidlActivity;

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

    void insertNotes(View v) {
        Intent intent = new Intent();
//        intent.setClassName("com.example.clientapp", "com.example.clientapp.AccpetActivity");
        intent.setAction("notes.intent.action.NOTE_VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        Uri data = Uri.parse("content://com.example.jin.NotesContentProvider/notes");
        intent.setDataAndType(data, "vnd.android.cursor.dir/NOTES");
        startActivity(intent);
    }

    public void onAidl(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, MyAidlActivity.class);
        startActivity(intent);
    }

    public void onSystemServer(View view) {
        Toast.makeText(this, "start jin service", Toast.LENGTH_SHORT).show();

        JinManager jinManager = (JinManager)getSystemService("jin_service");
        long start = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            jinManager.sendBehavior(4321);
        }
        long end = System.nanoTime();
        Log.e(TAG, "call send behavior costs:" + (end - start) + "ns");

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