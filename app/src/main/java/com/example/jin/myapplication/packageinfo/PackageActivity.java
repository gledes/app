package com.example.jin.myapplication.packageinfo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jin.myapplication.MainActivity;
import com.example.jin.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class PackageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package);
    }

    public void showPackageinfo(View v) {

        List<String> data = new ArrayList<String>();

        PackageManager pm = this.getPackageManager();
        List<PackageInfo> list = pm.getInstalledPackages(0);
//        StringBuffer sb = new StringBuffer();
        for (PackageInfo info : list) {
            if ((info.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
//                Log.i(MainActivity.tag, "MainActivity.getAppList, packageInfo=" + info.packageName);
//                sb.append(info.packageName).append("\n");
                data.add(info.packageName);

            }
        }

//        TextView textView = findViewById(R.id.packageinfo);
//        textView.setText(sb);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(PackageActivity.this, R.layout.item, data);
        ListView listView = findViewById(R.id.package_list);
        listView.setAdapter(adapter);

    }

    public void permissionActivity(View view) {
        Intent intent = new Intent();
        intent.setClass(PackageActivity.this, PermissionActivity.class);
        startActivity(intent);
    }

    public void showClient(View v) {
        Intent intent = new Intent();
        intent.setPackage("com.example.clientapp");
        PackageManager pm = getPackageManager();

        try {
            Drawable d = pm.getActivityBanner(intent);
            ComponentName componentName = new ComponentName("com.example.jin.myapplication", "com.example.jin.myapplication.contentprovider.NotesContentProvider");
            ProviderInfo providerInfo = pm.getProviderInfo(componentName, PackageManager.MATCH_ALL);
            ActivityInfo activityInfo = pm.getActivityInfo(new ComponentName("com.example.clientapp", "com.example.clientapp.MainActivity"), PackageManager.MATCH_ALL);
            ServiceInfo serviceInfo = pm.getServiceInfo(new ComponentName("com.example.clientapp", "com.example.clientapp.MessengerService"), PackageManager.MATCH_ALL);
//            PermissionInfo permissionInfo = pm.getPermissionInfo()
            String[] permission  = pm.getPackageInfo("com.example.clientapp", PackageManager.GET_PERMISSIONS).requestedPermissions;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        List<ResolveInfo> list = pm.queryIntentActivities(intent, PackageManager.MATCH_ALL);
        ArrayAdapter<ResolveInfo> adapter = new ArrayAdapter<ResolveInfo>(PackageActivity.this, R.layout.item, list) {
            public View getView(int position,  View convertView, ViewGroup parent) {

                TextView view = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
                ResolveInfo item = getItem(position);
                view.setText(item.activityInfo.name);

                return view;
            }
        };
        ListView listView = findViewById(R.id.package_list);
        listView.setAdapter(adapter);


    }
}
