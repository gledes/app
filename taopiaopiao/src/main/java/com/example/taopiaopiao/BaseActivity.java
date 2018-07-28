package com.example.taopiaopiao;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.alipaystander.AlipayInterface;

/**
 * Created by jin on 2018/7/28.
 */

public class BaseActivity extends Activity implements AlipayInterface {

    protected Activity that;

    @Override
    public void attach(Activity activity) {
        that = activity;
    }

    @Override
    public <T extends View> T findViewById(int id) {
        if (that == null) {
            return super.findViewById(id);
        } else {
            return that.findViewById(id);
        }

    }

    @Override
    public void setContentView(View view) {
        if (that == null) {
            super.setContentView(view);
        } else {
            that.setContentView(view);
        }

    }

    public ClassLoader getClassLoader() {
        if (that == null) {
            return super.getClassLoader();
        } else {
            return that.getClassLoader();
        }

    }

    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        if (that == null) {
            return super.getLayoutInflater();
        } else {
            return that.getLayoutInflater();
        }

    }

    @Override
    public Window getWindow() {
        if (that == null) {
            return super.getWindow();
        } else {
            return that.getWindow();
        }
    }

    @Override
    public WindowManager getWindowManager() {
        if (that == null) {
            return super.getWindowManager();
        } else {
            return that.getWindowManager();

        }

    }

    @Override
    public void onCreate(Bundle saveInstance) {
        if (that == null) {
            super.onCreate(saveInstance);
        } else {

        }

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public void onBackPressed() {

    }
}
