package com.example.jin.myapplication.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jin.myapplication.R;

/**
 * Created by jin on 2018/1/18.
 */

public class ServiceActivity extends Activity {


    private Button startService;

    private Button stopService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        startService = (Button)findViewById(R.id.startServiceBtn);
        startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ServiceActivity.this, FirstService.class);
                startService(intent);
            }
        });

        stopService = (Button)findViewById(R.id.stopServiceBtn);
        stopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ServiceActivity.this, FirstService.class);
                stopService(intent);
            }
        });

    }
}
