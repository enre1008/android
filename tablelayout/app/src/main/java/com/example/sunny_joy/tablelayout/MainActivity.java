package com.example.sunny_joy.tablelayout;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private FrameLayout layout;
    private TextView tvTouch, tvFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.framelayoutdemo2);

        initControl();
    }

    private void initControl() {
        layout=(FrameLayout)findViewById(R.id.layout);
        tvTouch=(TextView)findViewById(R.id.tvTouch);
        tvFinish=(TextView)findViewById(R.id.tvFinish);

        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_MOVE){
                    tvTouch.setVisibility(View.INVISIBLE);
                    tvFinish.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });
    }


}
