package com.example.sunny_joy.activitydemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * Created by sunny-joy on 2018/6/7.
 */

public class FourActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv1=new TextView(this);  //TextView构造的时候需要一个上下文，这里写this
        tv1.setText("这是第四个界面");
        tv1.setTextSize(40);
        tv1.setTextColor(Color.RED);

        setContentView(tv1);
    }
}
