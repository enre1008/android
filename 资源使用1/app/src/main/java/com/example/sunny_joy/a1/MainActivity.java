package com.example.sunny_joy.a1;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import static com.example.sunny_joy.a1.R.string.hello_world;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //在代码中引用
        //得到项目中的res文件夹
        Resources res = getResources();

        //2.得到资源中的值
        String strTest = res.getString(R.string.hello_world);
        Log.i("TEST","字符串为： " + strTest);
    }
}
