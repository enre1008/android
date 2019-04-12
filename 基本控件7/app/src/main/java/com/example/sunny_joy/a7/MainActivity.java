package com.example.sunny_joy.a7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private CheckBox chkJava, chkAndroid, chkArm;
    private TextView tvChoise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chkJava = (CheckBox)findViewById(R.id.chkJava);
        chkAndroid = (CheckBox)findViewById(R.id.chkAndroid);
        chkArm = (CheckBox)findViewById(R.id.chkArm);
        tvChoise = (TextView)findViewById(R.id.tvChoise);

        //设置事件监听
        CheckedChangeListener listener = new CheckedChangeListener();
        chkJava.setOnCheckedChangeListener(listener);
        chkAndroid.setOnCheckedChangeListener(listener);
        chkArm.setOnCheckedChangeListener(listener);

    }
    //内部类
    class CheckedChangeListener implements CompoundButton.OnCheckedChangeListener{
       /*
       * buttonView 发出事件的源
       * isChecked  控件是否被选中
       * */
        StringBuilder sb = new StringBuilder();
        String str;

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            switch(buttonView.getId()){
                case R.id.chkJava:
                    str="Java ";
                    changeString(str, isChecked);
                    break;

                case R.id.chkAndroid:
                    str="Android ";
                    changeString(str, isChecked);
                    break;

                case R.id.chkArm:
                    str="嵌入式开发 ";
                    changeString(str, isChecked);
                    break;
            }
            tvChoise.setText("您选中的课程是：" + sb.toString());
        }
        private void changeString(String str, boolean isChecked){
            //boolean isChecked;
            if(isChecked){
                //字符串连接
                sb.append(str);
            }else{
                //字符串删除
                int start=sb.indexOf(str);
                if(start!=-1){
                    //字符串存在
                    int end=str.length() + start;
                    sb.delete(start,end);
                }
            }

        }
    }
}
