package com.example.jin.myapplication.hadler;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.example.jin.myapplication.R;

public class HandlerThread2Activity extends AppCompatActivity {


    private TextView mTvServiceInfo;

    private HandlerThread mCheckMsgThread;

    private Handler mCheckMsgHandler;

    private boolean isUpdateInfo;

    private static final int MSG_UPDATE_INFO = 0x110;

    private Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_thread2);
        initBackThread();
        mTvServiceInfo = findViewById(R.id.id_textview);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        //开始查询
        isUpdateInfo = true;
        mCheckMsgHandler.sendEmptyMessage(MSG_UPDATE_INFO);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        //停止查询
        isUpdateInfo = false;
        mCheckMsgHandler.removeMessages(MSG_UPDATE_INFO);

    }

    private void initBackThread()
    {
        mCheckMsgThread = new HandlerThread("check-message-coming");
        mCheckMsgThread.start();

        mCheckMsgHandler = new Handler(mCheckMsgThread.getLooper())
        {
            public void handleMessage(Message msg)
            {
                checkForUpdate();
                if (isUpdateInfo)
                {
                    mCheckMsgHandler.sendEmptyMessage(MSG_UPDATE_INFO);
                }

            }

        };

    }

    private void checkForUpdate()
    {
        try {
            Thread.sleep(1000);
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    String result = "实时更新中，当前大盘指数：<font color='red'>%d</font>";
                    result = String.format(result,(int)(Math.random() * 3000 + 1000));
                    mTvServiceInfo.setText(Html.fromHtml(result));
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void onDestroy()
    {
        super.onDestroy();
        mCheckMsgThread.quit();
    }



}
