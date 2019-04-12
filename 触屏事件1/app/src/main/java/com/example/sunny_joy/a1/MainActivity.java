package com.example.sunny_joy.a1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPosition = (TextView)findViewById(R.id.tvPosition);
    }

    //MotionEvemt事件处理类
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //按下，移动，弹起
        int action = event.getAction();
        float x=event.getX();
        float y=event.getY();
        switch(action) {
//            case MotionEvent.ACTION_DOWN: //按下
//                Log.i("TEST","按下 X="+x+", Y="+y);
//                break;
            case MotionEvent.ACTION_MOVE: //移动
                Log.i("TEST","移动 X="+x+", Y="+y);
                tvPosition.setText("X="+x+", Y="+y);
                return true;
                //break;
//            case MotionEvent.ACTION_UP: //弹起
//                Log.i("TEST","弹起 X="+x+", Y="+y);
//                break;
        }

        //默认返回为false，表示在自身方法中没有做完，需要向上传递
        //如果认为在方法中事情做完了，可以返回true，即 return true;
        //return super.onTouchEvent(event);
        return true;
    }
}
