package com.example.sunny_joy.activitydemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * Created by sunny-joy on 2018/6/7.
 */

public class ThirdActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //取得传递的值
        Intent intent=getIntent();
        String hello=intent.getStringExtra("hello");

        TextView tv1=new TextView(this);  //TextView构造的时候需要一个上下文，这里写this
        tv1.setText(hello);  //将得到的值显示在TextView中
        tv1.setTextSize(40);
        tv1.setTextColor(Color.RED);

        setContentView(tv1);
    }
}
