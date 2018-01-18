package com.example.jin.myapplication.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import com.example.jin.myapplication.MainActivity;

/**
 * Created by jin on 2018/1/14.
 */

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(MainActivity.tag, "receive message");

        Bundle bundle = intent.getExtras();
        Object[] myObjects = (Object[])bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[myObjects.length];
        Log.i(MainActivity.tag, "length:" + messages.length);
        for (int i = 0; i < messages.length; i++) {
            messages[i] = SmsMessage.createFromPdu((byte[]) myObjects[i]);
            Log.i(MainActivity.tag, messages[i].getDisplayMessageBody());
        }
    }
}
