package com.example.sunny_joy.linerlayout1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import static android.widget.LinearLayout.HORIZONTAL;

public class MainActivity extends AppCompatActivity {

    private LinearLayout layout;
    private Button btnHor, btnVer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2);

        //初始化控件
        initControl();
    }

    //注册监听
    private void initControl() {
        layout=(LinearLayout)findViewById(R.id.layout);
        btnHor=(Button)findViewById(R.id.btnHor);
        btnVer=(Button)findViewById(R.id.btnVer);

        ChangeOrientation listener = new ChangeOrientation();
        btnHor.setOnClickListener(listener);
        btnVer.setOnClickListener(listener);
    }

    //内部类实现单击
    class ChangeOrientation implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btnHor:
                    layout.setOrientation(LinearLayout.HORIZONTAL);
                    break;
                case R.id.btnVer:
                    layout.setOrientation(LinearLayout.VERTICAL);
                    break;
            }
        }
    }
}
