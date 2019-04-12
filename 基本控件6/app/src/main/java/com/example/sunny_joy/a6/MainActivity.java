package com.example.sunny_joy.a6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //声明控件
    private RadioGroup rgCourse;
    private TextView tvChoise;
    private RadioButton radJava, radAndroid, radArm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //关联控件
        rgCourse = (RadioGroup)findViewById(R.id.rgCourse);
        tvChoise = (TextView)findViewById(R.id.tvChoise);

        radJava = (RadioButton)findViewById(R.id.radJava);
        radAndroid = (RadioButton)findViewById(R.id.radAndroid);
        radArm = (RadioButton)findViewById(R.id.radArm);

        //设置属性
        radArm.setChecked(true);

        //设置事件
        rgCourse.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
//                Log.i("TEST", "onCheckedChanged: " + checkedId);
//                Log.i("TEST", "Java: " + R.id.radJava);
//                Log.i("TEST", "Android " + R.id.radAndroid);
//                Log.i("TEST", "Arm " + R.id.radArm);
//                switch (checkedId){
//                    case R.id.radJava:
//                        tvChoise.setText("你选中的课程：是Java");
//                        break;
//                    case R.id.radAndroid:
//                        tvChoise.setText("你选中的课程是：Android");
//                        break;
//                    case R.id.radArm:
//                        tvChoise.setText("你选中的课程是：Arm");
//                        break;
//                }
                if(radJava.isChecked()){
                    tvChoise.setText("你选中的课程：是Java");
                }else if(radAndroid.isChecked()){
                    tvChoise.setText("你选中的课程是：Android");
                }else if(radArm.isChecked()){
                    tvChoise.setText("你选中的课程是：Arm");
                }

            }
        });


    }
}
