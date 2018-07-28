package com.example.alipay;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.File;

public class MainActivity extends AppCompatActivity {


    private String className;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PluginManager.getInstance().setContext(this);

    }

    public void load(View view) {
        File file = new File(Environment.getExternalStorageDirectory(), "plugin.apk");
        PluginManager.getInstance().load(file.getAbsolutePath());
    }

    public void click(View view) {
        Intent intent = new Intent(this, ProxyActivity.class);
        intent.putExtra("className", PluginManager.getInstance().getEnterActivityName());
        startActivity(intent);
    }
}
