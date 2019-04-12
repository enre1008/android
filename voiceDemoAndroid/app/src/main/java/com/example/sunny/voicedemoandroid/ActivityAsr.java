package com.example.sunny.voicedemoandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;

public class ActivityAsr extends Activity{
    private static final int REQUEST_UI = 1;

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asr);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener((v)-> {
            Intent intent = new Intent();
            intent.putExtra("decoder", 0);
            intent.setAction("com.baidu.action.RECOGNIZE_SPEECH");
            startActivityForResult(intent, REQUEST_UI);
        });
    }

    @Override
    protected void onActionResult(int requestCode, int resultCode, Intent data){
        super.onActiviyResult(requestCode, resultCode, data);
        if(resultCode == REQUEST_OK){
            ArrayList<String> nbest = data.getStringArrayListExtra(SpeechRecognizer.RESULTS_RECOGNITION);
            Log.d("speech", nbest.get(0));
        }
    }
}
