package com.example.jin.myapplication.service.intentservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jin.myapplication.R;

public class IntentServiceActivity extends AppCompatActivity {

    private static final String UPLOAD_RESULT = "com.zhy.blogcodes.intentservice.UPLOAD_RESULT";

    private static final String ACTION_UPLOAD_IMG = "com.zhy.blogcodes.intentservice.action.UPLOAD_IMAGE";

    private static final String EXTRA_IMG_PATH = "com.zhy.blogcodes.intentservice.extra.IMG_PATH";

    private LinearLayout mLyTaskContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);

        mLyTaskContainer = (LinearLayout) findViewById(R.id.id_ll_taskcontainer);

        registerReceiver();

    }

    private BroadcastReceiver uploadImgReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            if (UPLOAD_RESULT.equals(intent.getAction()))
            {
                String path = intent.getStringExtra(EXTRA_IMG_PATH);

                handleResult(path);

            }

        }
    };

    private void handleResult(String path)
    {
        TextView tv = (TextView) mLyTaskContainer.findViewWithTag(path);
        tv.setText(path + " upload success ~~~ ");
    }

    private void registerReceiver()
    {
        IntentFilter filter = new IntentFilter();
        filter.addAction(UPLOAD_RESULT);
        registerReceiver(uploadImgReceiver, filter);
    }

    int i = 0;

    public void addTask(View view)
    {
        //模拟路径
        String path = "/sdcard/imgs/" + (++i) + ".png";
        startUploadImg(path);

        TextView tv = new TextView(this);
        mLyTaskContainer.addView(tv);
        tv.setText(path + " is uploading ...");
        tv.setTag(path);
    }

    public void startUploadImg(String path)
    {
        Intent intent = new Intent(this, UploadImgService.class);
        intent.setAction(ACTION_UPLOAD_IMG);
        intent.putExtra(EXTRA_IMG_PATH, path);
        startService(intent);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        unregisterReceiver(uploadImgReceiver);
    }




}
