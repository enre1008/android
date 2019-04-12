package com.example.sunny_joy.a5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView image;
    private Button btnchange;
    private int[] resIds = {
            R.drawable.peony,
            R.drawable.pluto,
            R.drawable.tulips
    };
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView)findViewById(R.id.imageView1);
        btnchange = (Button)findViewById(R.id.btnchange);

        btnchange.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                index++;
                if(index > resIds.length - 1){
                    index = 0;
                }
                image.setImageResource(resIds[index]);
            }
        });


    }
}
