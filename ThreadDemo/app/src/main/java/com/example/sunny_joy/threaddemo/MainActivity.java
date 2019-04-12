package com.example.sunny_joy.threaddemo;

import android.app.Notification;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvTest;
    private Handler handler=new Handler(){
        public void handleMessage(android.os.Message msg){
            //处理消息，运行在主线程中
            switch (msg.what){
                case 0x0001:
                    int index=msg.arg1;
                    tvTest.setText(index+""); //允许，因为运行在主线程中
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTest=(TextView)findViewById(R.id.tvTest);
    }

    public void test(View view) {
        //按钮单击完成的工作
//        try {
//            Thread.sleep(20000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
        switch (view.getId()){
            case R.id.btnAsync:
                //使用线程完成
                //工作线程是一个匿名类
                new Thread() {
                    public void run() {
                        for(int i=0; i<100; i++){
                            //tvTest.setText(i+"");//不允许，子线程不可更新UI线程中控件的属性
                            Message msg=new Message();
                            msg.what=0x0001; //消息的what是消息标识，必须有
                            msg.arg1=i;
                            handler.sendMessage(msg);
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start();
            }
        }
}
