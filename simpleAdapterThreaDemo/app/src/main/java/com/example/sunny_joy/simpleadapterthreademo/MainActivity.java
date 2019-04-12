package com.example.sunny_joy.simpleadapterthreademo;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.INotificationSideChannel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final int MSG_FILLDATA_FINISH=0x0001;
    private static final int MSG_EXCEPTION=0x0002;

    private ListView listView;
    private List<Map<String, String>> data;

    private Handler handler=new Handler(){

        public void handleMessage(android.os.Message msg){
            switch(msg.what){
                case MSG_FILLDATA_FINISH:
                    SimpleAdapter adapter=new SimpleAdapter(
                            MainActivity.this,     //上下文
                            data,                  //数据源
                            R.layout.list_item,    //项布局
                            new String[]{"name", "age"},         //要显示对数据在数据源中相应的key
                            new int[]{R.id.tvName, R.id.tvAge}); //要显示的数据在布局文件中相应的控件的id
                    listView.setAdapter(adapter);
                    break;
                case MSG_EXCEPTION:
                    Toast.makeText(MainActivity.this, "数据出现异常无法显示", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=(ListView)findViewById(R.id.listView1);

        new Thread(){
            public void run(){
                try {
                    fillData();
                    //发送通知
                    handler.sendEmptyMessage(MSG_FILLDATA_FINISH);
                } catch (XmlPullParserException e) {
                    handler.sendEmptyMessage(MSG_EXCEPTION);
                } catch (IOException e) {
                    handler.sendEmptyMessage(MSG_EXCEPTION);
                }
            }
        }.start();
    }

    //解析XML，填充数据源data
    private void fillData() throws XmlPullParserException, IOException {
        List<Student> studentList = new ArrayList<Student>();
        Student stu= null;
        //解析XML
        XmlPullParser parser=getResources().getXml(R.xml.students);
        int eventType=parser.getEventType();
        while(eventType != XmlPullParser.END_DOCUMENT){
            switch(eventType){
                case XmlPullParser.START_DOCUMENT:
                    break;
                case XmlPullParser.START_TAG:
                    String name=parser.getName();
                    if(name.equals("student")){
                        stu=new Student();
                    }
                    if(name.equals("name")){
                        String stuName=parser.nextText();
                        stu.setName(stuName);
                    }
                    if(name.equals("age")){
                        String age=parser.nextText();
                        stu.setAge(age);
                    }
                    break;
                case XmlPullParser.END_TAG:
                    name=parser.getName();
                    if(name.equals("student")){
                        studentList.add(stu);
                    }
                    break;
            }
            eventType=parser.next();
        }
        //填充数据data
        data = new ArrayList<Map<String, String>>();
        for(Student s: studentList){
            Map<String, String> stu1=new HashMap<String,String>();  //一项
            stu1.put("name", s.getName());
            stu1.put("age", s.getAge());
            data.add(stu1);
        }
    }

    //类Student
    static class Student{
        private String name;
        private String age;

        public String getName() {
            return name;
        }

        public String getAge() {
            return age;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public Student() {
        }

        public Student(String name, String age) {
            this.name = name;
            this.age = age;
        }
    }
}
