package com.example.alipaystander;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

/**
 * Created by jin on 2018/7/28.
 */

public interface AlipayInterface {


    public void onCreate(Bundle saveInstance);

    public void onStart();

    public void onResume();

    public void stop();

    public void onDestroy();

    public void onSaveInstanceState(Bundle outState);

    public boolean onTouchEvent(MotionEvent event);

    public void onBackPressed();

    public void attach(Activity activity);

}
