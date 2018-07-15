package com.example.jin.myapplication.packageinfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.jin.myapplication.R;

public class ShowPermissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_permission);

        Intent intent = getIntent();
        String strPermission = intent.getStringExtra("strPermission");

        TextView textView = findViewById(R.id.show_permission);

        textView.setText(strPermission);


    }
}
