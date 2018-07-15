package com.example.jin.myapplication.packageinfo;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.jin.myapplication.MainActivity;
import com.example.jin.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PermissionActivity extends ListActivity {
    HashMap<String, String[]> hashMap = new HashMap<String, String[]>();
    List<String> dataAppList = new ArrayList<String>();
    List<String> systemAppList = new ArrayList<String>();

    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PackageManager pm = getPackageManager();
        Intent query = new Intent(Intent.ACTION_MAIN);
        query.addCategory("android.intent.category.LAUNCHER");
        @SuppressLint("WrongConstant")
        List<ResolveInfo> resolves = pm.queryIntentActivities(query, PackageManager.GET_ACTIVITIES);

        dataAppList.add(""); // take up the position, refresh it after getting dataApp size
        for (int i = 0; i < resolves.size(); i++) {
            ResolveInfo resolveInfo = resolves.get(i);
            String packageName = resolveInfo.loadLabel(pm).toString();
            String[] permission;
            try {
                // get permission list
                permission = pm.getPackageInfo(resolveInfo.activityInfo.packageName,
                        PackageManager.GET_PERMISSIONS).requestedPermissions;
                // app is system app ?
                if (isSystemApp(resolveInfo)) {
                    systemAppList.add(packageName);
                } else {
                    dataAppList.add(packageName);
                }
                hashMap.put(packageName, permission);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        dataAppList.set(0, "===== Data Apps(" + dataAppList.size() + ") =====");
        dataAppList.add("");
        dataAppList.add("===== System Apps(" + systemAppList.size() + ") =====");
        dataAppList.addAll(systemAppList);

        getListView().setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                dataAppList));
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                String[] permission = hashMap.get(dataAppList.get(position));
                StringBuilder stringBuilder = new StringBuilder();

                try {
                    for (int i = 0; i < permission.length; i++) {
                        stringBuilder.append(getPermissionDetailSys(permission[i]) + "\n");
                    }
                    Intent intent = new Intent(PermissionActivity.this, ShowPermissionActivity.class);
                    intent.putExtra("strPermission", dataAppList.get(position) + " has " +
                            permission.length + " Permission(s):\n\n" + stringBuilder);
                    startActivity(intent);
                } catch (Exception e) {
                    Log.e(MainActivity.tag, e.toString());
                    throw e;
                    // ToDo:Handle Exception
                }
            }
        });
    }

    private boolean isSystemApp(ResolveInfo resolveInfo) {
        if ((resolveInfo.activityInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * use system func to get normal permission info
     *
     * @param permissionName
     * @return string
     */
    public String getPermissionDetailSys(String permissionName) {
        StringBuilder strPermissionInfo = new StringBuilder();
        PackageManager pm = PermissionActivity.this.getPackageManager();

        try {
            // get permission info by name
            PermissionInfo permissionInfo = pm.getPermissionInfo(permissionName, 0);

            try {
                // get permission group
                PermissionGroupInfo permissionGroupInfo = pm.getPermissionGroupInfo(
                        permissionInfo.group, 0);
                strPermissionInfo.append("[").append(permissionGroupInfo.loadLabel(pm).toString()).append("]\n ");
            } catch (PackageManager.NameNotFoundException e) {

            }
            strPermissionInfo.append(permissionInfo.loadLabel(pm).toString()).append("(").append(permissionName).append("):\n");
            strPermissionInfo.append(permissionInfo.loadDescription(pm)).append("\n");
        } catch (PackageManager.NameNotFoundException e) {
            // use our func if permission info not matched
            return getPermissionDetail(permissionName) + "\n";
        }
        return strPermissionInfo.toString();
    }

    /**
     * if getPermissionDetailSys() is execute catch part, this func will run
     *
     * @param strPermission
     * @return description of permission, or permission name if not matched.
     */
    public String getPermissionDetail(String strPermission) {
        return strPermission;
    }

}
