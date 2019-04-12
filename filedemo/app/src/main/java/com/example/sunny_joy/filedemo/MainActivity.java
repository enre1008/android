package com.example.sunny_joy.filedemo;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText etContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etContent=(EditText)findViewById(R.id.etContent);
        etContent.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
    }

    //写入文件
    public void write(View view){
        String content=etContent.getText().toString();
        FileOutputStream fos=null;   //字节流
        BufferedWriter writer=null;  //字符流
        try {
            fos=openFileOutput("test.txt", Context.MODE_PRIVATE);   //可能抛异常，open时文件可能没找到
            writer=new BufferedWriter(new OutputStreamWriter(fos)); //用OutputStreamWriter将字节流转换为字符流
            writer.write(content);   //可能抛异常，IOException，可能没写进去
            writer.flush();
            Toast.makeText(this, "写入完成...", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
                try {
                    if(writer != null) {
                        writer.close();   //后开的先关
                    }
                    if(fos != null){
                        fos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public void read(View view){
        BufferedReader br=null;
        StringBuilder sb=new StringBuilder();
        try {
            br=new BufferedReader(new InputStreamReader(openFileInput("test.txt")));  //可能抛异常，FileNotFoundException
            String content=br.readLine();   //可能抛异常，IOException
            while (content!=null){
                sb.append(content);
                content=br.readLine();
            }
            etContent.setText(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(br!=null){
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //读取Raw中的文件
    public void readRaw(View view){
        InputStream is = getResources().openRawResource(R.raw.rawtest);
        BufferedReader br=new BufferedReader(new InputStreamReader(is));
        String content;
        StringBuilder sb=new StringBuilder();
        try {
            content=br.readLine();
            while(content!=null){
                sb.append(content);
                content=br.readLine();
            }
            etContent.setText(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(br!=null){
                    br.close();
                }
                if(is!=null){
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void write(OutputStream os) throws IOException {
        String content=etContent.getText().toString();
        BufferedWriter writer=null;
        try{
            writer=new BufferedWriter(new OutputStreamWriter(os));
            writer.write(content);
            writer.flush();
            Toast.makeText(this, "写入完成...", Toast.LENGTH_LONG).show();;
        }finally {
            if(writer != null){
                writer.close();
            }
        }
    }
    public void read(InputStream is) throws IOException {
        BufferedReader br=null;
        StringBuilder sb=new StringBuilder();
        try {
            br= new BufferedReader(new InputStreamReader(is));
            String content=br.readLine();
            while(content!=null){
                sb.append(content);
                content=br.readLine();
            }
            etContent.setText(sb.toString());
        } finally {
            if(br!=null){
                br.close();
            }
        }
    }
    //操作SD卡
    public void operatorSDCard(View view){
        //1.判断SD卡
        String state=Environment.getExternalStorageState();
        try {
            if(state.equals(Environment.MEDIA_MOUNTED)){
                //SD卡是可用
                //Toast.makeText(this,"当前手机的SD卡可用", Toast.LENGTH_LONG).show();
                switch (view.getId()){
                    case R.id.btnSDWrite:
                        //得到路径
                        File dir=Environment.getExternalStorageDirectory();
                        //Toast.makeText(this, dir.getCanonicalPath(),Toast.LENGTH_LONG).show();
                        File file=new File(dir, "test.txt");
                        FileOutputStream fos=null;
                        try {
                           // if (ContextCompat.checkSelfPermission(this,
                           //         Manifest.permission.WRITE_EXTERNAL_STORAGE != PackageManager.PERMISSION_GRANTED)) {
                           //    ActivityCompat.requestPermissions(this,
                           //             new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},WRITE_);
                                fos=new FileOutputStream(file);
                                write(fos);
                           // }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                if(fos!=null){
                                    fos.close();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case R.id.btnSDRead:
                        //InputStream
                        //得到路径
                        File dir2=Environment.getExternalStorageDirectory();
                        File file2=new File(dir2, "test.txt");
                        FileInputStream fis=null;
                        fis=new FileInputStream(file2);
                        read(fis);
                        if(fis!=null){
                            fis.close();
                        }
                        break;
                }
            }else{
                Toast.makeText(this,"当前手机的SD卡有问题", Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void write(OutputStreamWriter os){

    }
}
