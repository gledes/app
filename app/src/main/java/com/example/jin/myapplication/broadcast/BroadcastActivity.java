package com.example.jin.myapplication.broadcast;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jin.myapplication.R;

public class BroadcastActivity extends Activity {

    private Button broadcast;

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
    }
}
