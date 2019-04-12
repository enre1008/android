package com.example.sunny;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    private TextView tvText;
    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        public void handleMessage(android.os.Message msg){
            //处理消息，运行在主线程中
            switch (msg.what){
                case 0x0001:
                    int index=msg.arg1;
                    tvText.setText(index+" ");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvText=findViewById(R.id.tvText);
    }

    public void test(View view){
        switch (view.getId()){
            case R.id.btnAsync:
                //使用线程
                //工作线程是一个匿名类
                new Thread(){
                    public void run(){
                        for(int i=0; i<100; i++){
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
                break;
        }
    }
}
