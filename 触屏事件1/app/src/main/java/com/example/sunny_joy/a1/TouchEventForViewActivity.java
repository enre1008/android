package com.example.sunny_joy.a1;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by sunny-joy on 2018/5/21.
 */

public class TouchEventForViewActivity extends Activity {
    View view;
    TextView tvPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touchforview);

        //初始化控件，关联控件
        initControl();

        //在控件上注册监听
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_MOVE){
                    int x=(int)event.getX();
                    int y=(int)event.getY();
                    tvPosition.setText("X="+x+", Y=" +y);
                }
                return true; //view的事件做完之后，不希望再传给activity去完成，所以设成true
                //若设成false，鼠标移动显示相应坐标功能不能实现
                // 因为activity除了initControl()没做任何事情
            }
        });

    }

    private void initControl() {
        view = (View)findViewById(R.id.view1);
        tvPosition = (TextView)findViewById(R.id.tvPosition);
    }
}
