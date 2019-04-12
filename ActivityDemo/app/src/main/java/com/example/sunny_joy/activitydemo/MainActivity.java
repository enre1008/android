package com.example.sunny_joy.activitydemo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult=(TextView)findViewById(R.id.tvResult);
    }

    //启动SecondActivity，是显式intent
    public void startSecondActivity(View view){
        Intent intent=new Intent();
        //直接说明所要启动的Activity是哪一个
        intent.setClass(this, SecondActivity.class);
        //传值
        intent.putExtra("number1", 10);
        intent.putExtra("number2", 20);
        //启动
        //startActivity(intent);
        //启动Activity并且接受返回
        startActivityForResult(intent, 0x0001);
    }

    //当有返回时自动回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        //Log.i("TEST", "onActivityResult");
        if(requestCode==0x0001 && resultCode==0x0002){
            int result=data.getIntExtra("result", -1);
            tvResult.setText(result+" ");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /*
    启动ThirdActivity, 是隐式intent
     */
    public void startThirdActivity(View view){
        Intent intent=new Intent();
        //设置一个逻辑动作名
        intent.setAction("hi.action.test");
        //添加类别
        intent.addCategory("hi.category.test");
        //传递值到另一个组件
        intent.putExtra("hello", "你好！"); //键值的方式传

        startActivity(intent);
    }

    /*
    调用系统已在的界面
     */
    public void startSystemActivity(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("content://contacts/people/1"));
        startActivity(intent);
    }
}
