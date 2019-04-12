package com.example.sunny_joy.systemreceiverdemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class ReceivedSms extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "接收到一个短消息", Toast.LENGTH_LONG).show();
    }


}
