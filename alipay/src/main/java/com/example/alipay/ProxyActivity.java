package com.example.alipay;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.example.alipaystander.AlipayInterface;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ProxyActivity extends Activity {

    private AlipayInterface alipayInterface;

    public String className;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        className = intent.getStringExtra("className");

        try {
            Class activity = getClassLoader().loadClass(className);
            Constructor constructor = activity.getConstructor(new Class[]{});
            alipayInterface = (AlipayInterface)(constructor.newInstance());
//            AlipayInterface alipayInterface = (AlipayInterface)(activity.newInstance());
            alipayInterface.attach(this);
            alipayInterface.onCreate(savedInstanceState);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        alipayInterface.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        alipayInterface.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        alipayInterface.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        alipayInterface.onp
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        alipayInterface.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        alipayInterface.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        alipayInterface.onBackPressed();
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        return alipayInterface.onTouchEvent(event);
//    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getDexClassLoader();
    }

    @Override
    public Resources getResources() {
        return PluginManager.getInstance().getResources();
    }

    @Override
    public void startActivity(Intent intent) {
        String className = intent.getStringExtra("className");
        Intent newIntent = new Intent(this, ProxyActivity.class);
        newIntent.putExtra("className", className);
        super.startActivity(intent);
    }
}
