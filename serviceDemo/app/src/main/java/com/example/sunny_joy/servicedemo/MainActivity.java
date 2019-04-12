package com.example.sunny_joy.servicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.sunny_joy.servicedemo.service.MyService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnStart, btnStop;
    private Button  btnBind, btnUnBind;

    private ServiceConnection conn=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //连接无效时调用
            Log.i("Activity", "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //连接成功时，自动调用

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        btnStart=(Button) findViewById(R.id.btnStart);
        btnStop=(Button)findViewById(R.id.btnStop);

        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);

        btnBind=(Button)findViewById(R.id.btnBind);
        btnUnBind=(Button)findViewById(R.id.btnUnBind);

        btnBind.setOnClickListener(this);
        btnUnBind.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=null;
        switch ((v.getId())){
            case R.id.btnStart:
                intent=new Intent(this, MyService.class); //onCreate -> onStartCommand
                startService(intent);
                break;
            case R.id.btnStop:
                intent=new Intent(this, MyService.class);
                stopService(intent);
                break;
            case R.id.btnBind:
                intent=new Intent(this, MyService.class);
                bindService(intent, conn, BIND_AUTO_CREATE);
                    //bindService必须要又一个连接存在, onCreate -> onBind
                break;
            case R.id.btnUnBind:
                intent=new Intent(this, MyService.class);
                unbindService(conn);
                break;
        }
    }
}
