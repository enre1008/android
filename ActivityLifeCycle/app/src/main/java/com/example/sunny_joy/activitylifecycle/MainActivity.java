package com.example.sunny_joy.activitylifecycle;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnStart;
    private TextView tvDisplay;

    private int count=0;
    private boolean mainFlag=false;
    private boolean isPause=true;

    private Handler handler=new Handler();

    //onCreate()方法在Activity的生命周期中，只调用一次
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart=(Button)findViewById(R.id.btnStart);
        tvDisplay=(TextView)findViewById(R.id.tvDisplay);

        new Thread(){
            public void run(){
                mainFlag=true;
                while (mainFlag) {
                    if(!isPause)  {//是否暂停
                        count++;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //运行在主线程中
                                tvDisplay.setText(count + " ");
                            }
                        });
                    }
                }
            }
        }.start();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //使线程暂停及继续运行
                isPause=!isPause;
            }
        });
    }

    //在Activity不可见
    @Override
    protected void onStart(){
        super.onStart();
    }

    //可见
    @Override
    protected void onResume(){
        super.onResume();
        isPause=false;
    }

    //可见，但已失去焦点
    @Override
    protected void onPause(){
        super.onPause();
        isPause=true;
    }

    //不可见
    @Override
    protected void onStop(){
        super.onStop();
    }

    //在整个生命周期中只调用一次
    @Override
    protected void onDestroy(){
        super.onDestroy();
        mainFlag=false;
    }
}
