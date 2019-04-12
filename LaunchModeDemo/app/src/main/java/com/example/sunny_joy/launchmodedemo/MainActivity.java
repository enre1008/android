package com.example.sunny_joy.launchmodedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvActivityCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvActivityCode=findViewById(R.id.tvActivityCode);
        tvActivityCode.setText(this.toString());
    }

    //Standrd启动模式
    public void sdandard(View view){
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);


    }
}
