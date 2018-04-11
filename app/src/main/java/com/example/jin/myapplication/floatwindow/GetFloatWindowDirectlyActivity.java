package com.example.jin.myapplication.floatwindow;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.jin.myapplication.R;

public class GetFloatWindowDirectlyActivity extends Activity {

    private static final int OVERLAY_PERMISSION_REQ_CODE = 0x001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_float_window_directly);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case OVERLAY_PERMISSION_REQ_CODE:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (!Settings.canDrawOverlays(this)) {
                            Toast.makeText(GetFloatWindowDirectlyActivity.this, "权限授予失败，无法开启悬浮窗", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // TODO: 18/1/7 已经授权
                        Toast.makeText(GetFloatWindowDirectlyActivity.this, "权限授予成功", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }

    public void onClick(View v) {

    }
}
