package com.example.sunny_joy.activitydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by sunny-joy on 2018/6/7.
 */

public class SecondActivity extends Activity {
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //得到传递当值
        final Intent intent=getIntent();
        final int num1=intent.getIntExtra("number1", -1);
        final int num2=intent.getIntExtra("number2", -1);

        btnBack=(Button)findViewById(R.id.btnback);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result=calc(num1,num2);
                intent.putExtra("result", result);
                setResult(0x0002, intent); //返回结果
                finish();
            }
        });
    }
    private int calc(int num1, int num2){
        return num1+num2;
    }
}
