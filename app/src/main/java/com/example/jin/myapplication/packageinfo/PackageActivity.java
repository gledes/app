package com.example.jin.myapplication.packageinfo;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.jin.myapplication.MainActivity;
import com.example.jin.myapplication.R;

import java.util.List;

public class PackageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package);
    }

    public void showPackageinfo(View v) {
        PackageManager pm = this.getPackageManager();
        List<PackageInfo> list = pm.getInstalledPackages(0);

        StringBuffer sb = new StringBuffer();
        for (PackageInfo info : list) {
            if ((info.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
//                Log.i(MainActivity.tag, "MainActivity.getAppList, packageInfo=" + info.packageName);
                sb.append(info.packageName).append("\n");

            }
        }

        TextView textView = findViewById(R.id.packageinfo);
        textView.setText(sb);

    }
}
