package com.example.sunny_joy.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //1。声明控件
    private Button btnTest;
    private EditText etContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //2. 关联控件
        btnTest = (Button)findViewById(R.id.button1);
        etContent = (EditText)findViewById(R.id.etContent);

        etContent.setText("Hello World");

//        Listener listener = new Listener();
//        //3. 第一种方法之，代码中注册
//        btnTest.setOnClickListener(
//                //实现接口的类对象View.onClickListener
//                listener
//        );

        //第二种方法，匿名类
//        btnTest.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v){
//                //事件处理
//                Log.i("TEST2", "以匿名的方式被单击了");
//            }
//        });

    }

    //第三种，当以XML形式设置事件时，回调
    //要求方法当原型与OnclickListener中定义的方法原型完全一致，即该方法一定要有View类型的参数
    public  void click(View v){
        //事件处理程序
        Log.i("TEST3","XML设置被单击了");
        etContent.setText("你好！哈哈哈哈");
    }

    //第一种方法之，内部类
    class Listener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //回调的方法，事件处理程序
            Log.i("TEST", "按钮被单击了");
        }
    }
}
