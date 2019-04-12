package com.example.sunny_joy.servicedemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by sunny-joy on 2018/6/12.
 */

public class ComputeService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new ComputeBinder();
    }

    public class ComputeBinder extends Binder{
        public double calcAvg(double...scores){ //double...可以是可变数组，参数可以是0～N个
            int count=scores.length;
            if(count==0){
                return 0;
            }
            double sum=0;
            for(double s:scores){
                sum+=s;
            }
            return sum/count;
        }
    }
}
