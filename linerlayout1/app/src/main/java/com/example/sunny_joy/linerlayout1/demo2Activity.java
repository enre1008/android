package com.example.sunny_joy.linerlayout1;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

/**
 * Created by sunny-joy on 2018/5/22.
 */

public class demo2Activity extends Activity {

    private LinearLayout layout;
    private CheckBox[] chk;
    private int[] gravity={Gravity.TOP, Gravity.BOTTOM, Gravity.LEFT, Gravity.RIGHT, Gravity.CENTER};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //关联布局文件
        setContentView(R.layout.activity_demo2);

        //初始化控件
        initControl();

    }

    private void initControl() {
        layout=findViewById(R.id.layout);
        int count=gravity.length;
        chk=new CheckBox[count];

        CheckedChangeListener listener = new CheckedChangeListener();
        for(int i=0; i<chk.length; i++){
            chk[i]=(CheckBox) layout.getChildAt(i);
            chk[i].setOnCheckedChangeListener(listener);
        }
    }

    class CheckedChangeListener implements CompoundButton.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            //互斥性
            if(buttonView==chk[0]){
                if(isChecked){
                    chk[1].setChecked(false);
                    chk[4].setChecked(false);
                }
            }
            if(buttonView==chk[1]){
                if(isChecked){
                    chk[0].setChecked(false);
                    chk[4].setChecked(false);
                }
            }
            if(buttonView==chk[2]){
                if(isChecked){
                    chk[3].setChecked(false);
                    chk[4].setChecked(false);
                }
            }
            if(buttonView==chk[3]){
                if(isChecked){
                    chk[2].setChecked(false);
                    chk[4].setChecked(false);
                }
            }
            if(buttonView==chk[4]){
                if(isChecked){
                    chk[0].setChecked(false);
                    chk[1].setChecked(false);
                    chk[2].setChecked(false);
                    chk[3].setChecked(false);
                }
            }

            int checkedGravity=0;
            for(int i=0; i<chk.length; i++){
                if(chk[i].isChecked()){
                    checkedGravity|=gravity[i];
                }
            }
            layout.setGravity(checkedGravity);
        }
    }


}
