package com.example.sunny_joy.servicedemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;


import com.example.sunny_joy.servicedemo.service.ComputeService;

/**
 * Created by sunny-joy on 2018/6/12.
 */

public class DemoActivity extends Activity {
    private EditText etChinese, etMath, etEnglish;
    private TextView tvResult;
    private Button btnOk, btnDisplay;

    private ComputeService.ComputeBinder binder=null;

    private ServiceConnection conn=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("TEST", "onServiceConnected");
            binder=(ComputeService.ComputeBinder)service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        init();
    }

    private void init() {
        etChinese=(EditText)findViewById(R.id.etChinese);
        etMath=(EditText)findViewById(R.id.etMath);
        etEnglish=(EditText)findViewById(R.id.etEnglish);

        tvResult=(TextView)findViewById(R.id.tvResult);

        btnOk=(Button)findViewById(R.id.btnOk);
        btnDisplay=(Button)findViewById(R.id.btnDisplay);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TEST", "bindService...");
                //启动Service
                Intent service=new Intent(DemoActivity.this, ComputeService.class);
                bindService(service, conn, Context.BIND_AUTO_CREATE);
                Log.i("TEST", "OK...bindService");
            }
        });
        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    //从文本框中读取输入的成绩
                    double chinese=Double.parseDouble(etChinese.getText().toString());
                    double math=Double.parseDouble(etMath.getText().toString());
                    double english=Double.parseDouble(etEnglish.getText().toString());
                    //请求对象完成工作
                    double result=binder.calcAvg(chinese, math, english);
                    //显示
                    tvResult.setText("三门课平均成绩为：" +result);
                }catch (NumberFormatException ex){

                }catch (Exception ex){

                }
            }
        });
    }
}
