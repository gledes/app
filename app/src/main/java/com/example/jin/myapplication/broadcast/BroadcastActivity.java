package com.example.jin.myapplication.broadcast;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jin.myapplication.R;

public class BroadcastActivity extends Activity {


    private static final String SMS_ACTION = "android.provider.Telephony.SMS_RECEIVED";

    private Button broadcast;

    private Button registerButton;

    private Button unRegisterButton;

    private SmsReceiver smsReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        broadcast = (Button)findViewById(R.id.broadcastBtn);
        broadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(BroadcastActivity.this, TestReceiver.class);
                BroadcastActivity.this.sendBroadcast(intent);
            }
        });

        registerButton = (Button)findViewById(R.id.registerBtn);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smsReceiver = new SmsReceiver();
                IntentFilter filter = new IntentFilter();
                filter.addAction(SMS_ACTION);
                BroadcastActivity.this.registerReceiver(smsReceiver, filter);
            }
        });

        unRegisterButton = (Button)findViewById(R.id.unRegisterBtn);
        unRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BroadcastActivity.this.unregisterReceiver(smsReceiver);

            }
        });



    }
}
