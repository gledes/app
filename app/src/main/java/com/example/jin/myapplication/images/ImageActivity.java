package com.example.jin.myapplication.images;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.jin.myapplication.R;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        ListView listView = findViewById(R.id.lv_images);
        ListAdapter adapter = new FIUImageAdapter(this);
        listView.setAdapter(adapter);
    }
}
