package com.example.sunny_joy.a4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //声明控件
    private TextView tx1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //关联
        String cachePath1 = getApplicationContext().getCacheDir().getPath();
        tx1 = (TextView) findViewById(R.id.tx1);
        tx1.setText(cachePath1);
    }


}
