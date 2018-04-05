package com.example.jin.myapplication.service.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import com.example.jin.myapplication.MainActivity;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class UploadImgService extends IntentService {

    private static final String UPLOAD_RESULT = "com.zhy.blogcodes.intentservice.UPLOAD_RESULT";

    private static final String ACTION_UPLOAD_IMG = "com.zhy.blogcodes.intentservice.action.UPLOAD_IMAGE";

    private static final String EXTRA_IMG_PATH = "com.zhy.blogcodes.intentservice.extra.IMG_PATH";


    public UploadImgService() {
        super("UploadImgService");
    }



    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_UPLOAD_IMG.equals(action))
            {
                final String path = intent.getStringExtra(EXTRA_IMG_PATH);
                handleUploadImg(path);
            }

        }
    }

    private void handleUploadImg(String path)
    {
        try
        {
            //模拟上传耗时
            Thread.sleep(3000);

            Intent intent = new Intent(UPLOAD_RESULT);
            intent.putExtra(EXTRA_IMG_PATH, path);
            sendBroadcast(intent);

        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }


    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.e(MainActivity.tag,"onCreate");
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.e(MainActivity.tag,"onDestroy");
    }

}
