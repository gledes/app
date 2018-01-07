package com.example.jin.myapplication.contentprovider;

import android.app.Activity;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.jin.myapplication.MainActivity;
import com.example.jin.myapplication.R;

public class ContentProviderActivity extends Activity {

    private Button insertButton;

    private Button queryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
        insertButton = (Button)findViewById(R.id.insertBtn);
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put(FirstProviderMetaData.UserTableMetaData.USER_NAME, "张三");
                Uri uri = getContentResolver().insert(FirstProviderMetaData.UserTableMetaData.CONTENT_URI, values);
                Log.i(MainActivity.tag, "uri--->" + uri);
            }
        });
    }
}
