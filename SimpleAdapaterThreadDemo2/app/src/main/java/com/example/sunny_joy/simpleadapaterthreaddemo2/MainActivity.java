package com.example.sunny_joy.simpleadapaterthreaddemo2;

import android.app.Notification;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.INotificationSideChannel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
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
    private ListView listView;
    private List<Map<String, String>> data;

    private static final int MSG_FILLDATA_FINISH=0x0001;
    private static final int MSG_EXCEPTION=0x0002;

    private Handler handler=new Handler(){
      public void handleMessage(Message msg){
          switch (msg.what){
              case MSG_FILLDATA_FINISH:
                  SimpleAdapter adapter=new SimpleAdapter(
                          MainActivity.this,
                          data,
                          R.layout.list_items,
                          new String[]{"name", "age"},
                          new int[]{R.id.tvName, R.id.tvAge}
                  );
                  listView.setAdapter(adapter);
                  break;
              case MSG_EXCEPTION:
                  Toast.makeText(MainActivity.this, "数据异常无法显示", Toast.LENGTH_LONG).show();
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
                    handler.sendEmptyMessage(MSG_FILLDATA_FINISH);
                } catch (XmlPullParserException e) {
                    handler.sendEmptyMessage(MSG_EXCEPTION);
                    e.printStackTrace();
                } catch (IOException e) {
                    handler.sendEmptyMessage(MSG_EXCEPTION);
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void fillData() throws XmlPullParserException, IOException {
        List<Student> studentList= new ArrayList<Student>();
        Student stu= null;

        XmlPullParser parser=getResources().getXml(R.xml.students);
        int eventType=parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT){
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
                        String stuAge=parser.nextText();
                        stu.setAge(stuAge);
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
        data=new ArrayList<Map<String,String>>();
        for(Student s:studentList){
            Map<String,String> map = new HashMap<String,String>();
            map.put("name", s.getName());
            map.put("age", s.getAge());
            data.add(map);
        }
    }

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
