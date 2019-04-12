package com.example.sunny.androidluyin;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button Startrecording, Stoprecording, Playtape;
    private MediaRecorder recorder;
    private MediaPlayer player;
    private String voicePath;
    private long time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //new一个实例
        player = new MediaPlayer();
        Startrecording = findViewById(R.id.Startrecording);
        Stoprecording = findViewById(R.id.Stoprecord);
        Playtape = findViewById(R.id.Playtape);

        //点击事件
        Playtape.setOnClickListener(this);
        Startrecording.setOnClickListener(this);
        Stoprecording.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.Startrecording: //开始录音
                fasong();
                break;
            case R.id.Stoprecord:
                jieshu();
                break;
            case R.id.Playtape:
                play();
                break;
            default:
                break;
        }
    }

    //发送语音
    private void fasong(){
        if(recorder != null){
            recorder.release();
        }else{
            recorder = new MediaRecorder();
        }

        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        //输出格式
        recorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_WB);
        //设置音频编码器
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        //缓存目录
        String str = getApplicationContext().getCacheDir().getPath();
        //设置文件名
        voicePath = str + System.currentTimeMillis()+ ".amr";
        //设置录音的输出路径
        recorder.setOutputFile(voicePath);

        try {
            recorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        recorder.start();
        time = System.currentTimeMillis();
    }

    //结束语音
    private void jieshu(){
        recorder.stop();
        long shijian = System.currentTimeMillis() - time;
        if(shijian < 1000){//判断，如果录音时间小于一秒，则删除文件并提示过短
            File file = new File(voicePath);
            if(file.exists()){//判断文件师范存在，如果存在则删除文件
                file.delete();//删除文件
                Toast.makeText(MainActivity.this, "录音时间过短", Toast.LENGTH_SHORT).show();
            }
        }
        //重置
        recorder.release();
        if(recorder != null){
            recorder.release();
            recorder = null;
            System.gc();

        }
    }

    //播放录音
    private void play(){
        if(player != null ){
            player.reset();
            try {
                //设置语言的来源
                player.setDataSource(voicePath);
                //初始化
                player.prepare();
                //开始播放
                player.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
