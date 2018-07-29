package com.example.alipay;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.example.alipaystander.AlipayInterface;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ProxyActivity extends Activity {
    public static String TAG = "ProxyActivity";
    public String className;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        className = intent.getStringExtra("className");

        try {
            Class activity = getClassLoader().loadClass(className);
            Constructor constructor = activity.getConstructor(new Class[]{});
            AlipayInterface alipayInterface = (AlipayInterface)(constructor.newInstance());
//            AlipayInterface alipayInterface = (AlipayInterface)(activity.newInstance());
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
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getDexClassLoader();
    }

    @Override
    public Resources getResources() {
        return PluginManager.getInstance().getResources();
    }
}
