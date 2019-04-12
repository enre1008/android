package com.example.sunny_joy.xmlparserdemo;

import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int MSG_FINISH=0x0001;
    private TextView tvContents;
    private Handler handler=new Handler(){
        public void handlerMessage(android.os.Message msg){
            switch (msg.what){
                case MSG_FINISH:
                    List<String> contents=(List<String>)msg.obj;
                    //在主线程中显示
                    for(String content:contents){
                        tvContents.append(content+"\n");
                    }
                    break;
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvContents=(TextView)findViewById(R.id.tvContents);
    }
    //事件处理

    public void parser(View view){
        new Thread(){
            public void run(){
                try {
                     List<String> contents=getPullParserContents(getResources(), R.xml.words);
                     //完成工作，通知主线程
                    Message msg=handler.obtainMessage();
                    msg.what=MSG_FINISH;
                    msg.obj=contents;

                    handler.sendMessage(msg);
                }
                catch (XmlPullParserException e) {
                  e.printStackTrace();
                } catch (IOException e) {
                  e.printStackTrace();
                }
            }
        }.start();

//        try {
//            List<String> contents=getPullParserContents(getResources(), R.xml.words);
//            for (String content:contents){
//                tvContents.append(content+"\n");
//            }
//        } catch (XmlPullParserException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private List<String> getPullParserContents(Resources res, int id) throws
            XmlPullParserException, IOException {
        List<String> contents=null;
        String tagName;
        //XMLPullParser
        //XmlPullParser parser = getResources().getXml(R.xml.words);
        XmlPullParser parser = res.getXml(id);
        //Pull解析本质是SAX解析

            int eventType = parser.getEventType();
            while(eventType!=XmlPullParser.END_DOCUMENT){
                switch (eventType){
                    case XmlPullParser.START_DOCUMENT: //文档开始
                        //Log.i("TEST","START_DOCUMENT");
                        contents=new ArrayList<String>();
                        break;
                    case XmlPullParser.END_DOCUMENT://文档结束
                        Log.i("TEST","END_DOCUMENT");
                        break;
                    case  XmlPullParser.START_TAG: //标记（标签，元素，Node, Element, Tag）
                        tagName=parser.getName();
                        if(tagName.equals("word")){
                            String value = parser.getAttributeValue(0);
                            contents.add(value);
                            Log.i("TEST", "START_TAG: " + tagName + "Value: " + value);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        tagName=parser.getName();
                        Log.i("TEST","END_TAG: " + tagName);
                        break;
                }
                eventType=parser.next();
            }
        return contents;
    }
}
