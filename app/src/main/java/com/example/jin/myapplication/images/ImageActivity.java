package com.example.jin.myapplication.images;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jin.myapplication.MainActivity;
import com.example.jin.myapplication.R;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        requestReadExternalPermission();

    }

    private void requestReadExternalPermission() {
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Log.d(MainActivity.tag, "READ permission IS NOT granted...");

            if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {

                Log.d(MainActivity.tag, "11111111111111");
            } else {
                // 0 是自己定义的请求coude
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                Log.d(MainActivity.tag, "222222222222");
            }
        } else {
            Log.d(MainActivity.tag, "READ permission is granted...");
            ListView listView = findViewById(R.id.lv_images);
            ListAdapter adapter = new FIUImageAdapter(this);
            listView.setAdapter(adapter);
        }
    }


    public static boolean isGrantExternalRW(Activity activity, int requestCode) {

            int storagePermission = activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int cameraPermission = activity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);

            if (storagePermission != PackageManager.PERMISSION_GRANTED ||
                    cameraPermission != PackageManager.PERMISSION_GRANTED) {
//                activity.requestPermissions(PERMISSIONS_CAMERA_AND_STORAGE, requestCode);
                return false;
            }
        return true;
    }



    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Login(loginId, loginPsd);
                    ListView listView = findViewById(R.id.lv_images);
                    ListAdapter adapter = new FIUImageAdapter(this);
                    listView.setAdapter(adapter);
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "您的手机暂不适配哦~", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
