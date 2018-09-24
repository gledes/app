package com.example.jin.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jin.myapplication.broadcast.BroadcastActivity;
import com.example.jin.myapplication.calculate.CalculateActivity;
import com.example.jin.myapplication.contentprovider.ContentProviderActivity;
import com.example.jin.myapplication.floatwindow.GetFloatWindowDirectlyActivity;
import com.example.jin.myapplication.fragment.MainFragmentActivity;
import com.example.jin.myapplication.hadler.HandlerThread2Activity;
import com.example.jin.myapplication.images.ImageActivity;
import com.example.jin.myapplication.notification.NotificationUtils;
import com.example.jin.myapplication.packageinfo.PackageActivity;
import com.example.jin.myapplication.service.ServiceActivity;
import com.example.jin.myapplication.service.intentservice.IntentServiceActivity;
import com.example.jin.myapplication.sqlite.SQLiteActivity;
import com.example.jin.myapplication.webviewfiledemo.WebViewActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    public static final String tag = "MyApp";

    private List<TextData> list = new ArrayList<TextData>();


    class TextData {

        String name;

        View.OnClickListener listener;

        public TextData(String name) {
            this.name = name;
        }

        public TextData(String name, View.OnClickListener listener) {
            this.name = name;
            this.listener = listener;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public View.OnClickListener getListener() {
            return listener;
        }

        public void setListener(View.OnClickListener listener) {
            this.listener = listener;
        }
    }


    void init() {
        list.add(new TextData("start", new StartButtonListener()));

        list.add(new TextData("end", new EndButtonListener()));

        list.add(new TextData("progress bar", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ProgressBarActivity.class);
                MainActivity.this.startActivity(intent);
            }
        }));

        list.add(new TextData("Hadler Thread", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, HadlerThreadActivity.class);
                MainActivity.this.startActivity(intent);
            }
        }));

        list.add(new TextData("Hadler Thread2", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, HandlerThread2Activity.class);
                startActivity(intent);
            }
        }));

        list.add(new TextData("SQLite Test", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SQLiteActivity.class);
                MainActivity.this.startActivity(intent);
            }
        }));

        list.add(new TextData("Content Provider", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ContentProviderActivity.class);
                MainActivity.this.startActivity(intent);
            }
        }));

        list.add(new TextData("Broadcast", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, BroadcastActivity.class);
                MainActivity.this.startActivity(intent);
            }
        }));

        list.add(new TextData("Service", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ServiceActivity.class);
                MainActivity.this.startActivity(intent);
            }
        }));

        list.add(new TextData("Web View", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, WebViewActivity.class);
                MainActivity.this.startActivity(intent);
            }
        }));

        list.add(new TextData("Intent Service", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, IntentServiceActivity.class);
                MainActivity.this.startActivity(intent);
            }
        }));

        list.add(new TextData("Notification", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationUtils notificationUtils = new NotificationUtils(MainActivity.this);
                notificationUtils.sendNotification("测试标题", "测试内容");
            }
        }));

        list.add(new TextData("Float Window", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, GetFloatWindowDirectlyActivity.class);
                MainActivity.this.startActivity(intent);
            }
        }));

        list.add(new TextData("ImageView", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ImageActivity.class);
                MainActivity.this.startActivity(intent);
            }
        }));

        list.add(new TextData("package info", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, PackageActivity.class);
                MainActivity.this.startActivity(intent);
            }
        }));

        list.add(new TextData("fragment", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MainFragmentActivity.class);
                MainActivity.this.startActivity(intent);
            }
        }));

        list.add(new TextData("calculate", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CalculateActivity.class);
                MainActivity.this.startActivity(intent);
            }
        }));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        ArrayAdapter<TextData> adapter = new ArrayAdapter<TextData>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, list) {
            public View getView(int position,  View convertView, ViewGroup parent) {

                View view = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_expandable_list_item_1, parent, false);
                TextView text = (TextView)view;
                TextData item = getItem(position);
                text.setText(item.getName());
                text.setOnClickListener(item.getListener());
                return view;
            }
        };
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);


    }



    class StartButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            handler.post(updateThread);
        }
    }

    class EndButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            handler.removeCallbacks(updateThread);
        }
    }

    Handler handler = new Handler();
    Runnable updateThread = new Runnable() {
        @Override
        public void run() {
            System.out.print("UpdateThread");
            Log.i(tag, "UpdateThread");
            handler.postDelayed(updateThread, 3000);
        }
    };
}
