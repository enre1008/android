package com.example.sunny_joy.servicedemo.service;

import android.app.Service;
import android.content.Intent;
import android.nfc.TagLostException;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by sunny-joy on 2018/6/11.
 */

public class MyService extends Service {
    private static final String TAG="MyService";

    //只有生命周期的第一次才被调用
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        super.onDestroy();
    }

    //每次startService启动会被调用
    @Override
    public int onStartCommand(Intent intent,  int flags, int startId) {
        Log.i(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);

    }

    //bindService启动时调用，在整个生命周期中只被调用一次
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        return new MyBinder(); //必须返回一个对象，才能表示连接成功了
    }

    class MyBinder extends android.os.Binder{

    }
}
