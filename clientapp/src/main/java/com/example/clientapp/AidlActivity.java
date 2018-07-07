package com.example.clientapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mark.aidl.AidlRemote;
import com.mark.aidl.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AidlActivity extends AppCompatActivity {

    Button btnAdd;
    TextView hello;

    List<Person> mPersons;
    // 声明AidlRemote接口
    AidlRemote mAidlRemote;

    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 当服务绑定成功之后会调用此方法，
            // 这里调用AidlRemote.Stub.asInterface静态方法将service参数转成AidlRemote实例
            mAidlRemote = AidlRemote.Stub.asInterface(service);
            Log.d(MainActivity.TAG, "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // 当断开服务时，回收资源
            mAidlRemote = null;
            Log.d(MainActivity.TAG, "onServiceDisconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);

        // 创建Person数据
        mPersons = getPersonList(10);

    }

    protected void onStop()
    {
        super.onStop();

    }

    /**
     * 创建Person数据
     * @param num 创建多少个Person对象
     * @return
     */
    private List<Person> getPersonList(int num) {
        List<Person> persons = new ArrayList<>();
        for(int i = 0;i<num;i++) {
            Person person = new Person("Person"+i, 20 + i);
            persons.add(person);
        }
        return persons;
    }

    public void avg(View v) {
        hello = (TextView) findViewById(R.id.hello);


        Random rand = new Random();
        int age = rand.nextInt(100);
        mPersons.add(new Person("person", age));
        // 计算mPersons中Person的平均年龄
        if (mAidlRemote != null) {
            try {
                float average = mAidlRemote.getAverageAge(mPersons);
                hello.setText("平均年龄为：" + average);
                Log.d(MainActivity.TAG, "平均年龄为：" + average);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getApplicationContext(), "服务器被异常杀死，请重新绑定服务端" , Toast.LENGTH_SHORT).show();
        }



    }

    /**
     * 绑定到服务端的求平均值服务
     */
    public void bindService(View view) {
        // 获取到服务端
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.example.jin.myapplication", "com.mark.aidl.RemoteService"));
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    /**
     * 点击unBindService按钮时调用
     * @param view
     */
    public void unbindService(View view)
    {
        Log.d(MainActivity.TAG, "unbindService");
        unbindService(conn);
    }
}
