package com.example.sunny_joy.simpleadapterdemo;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private static final int MSG_FILLDATA_FINISH=0x0001;
    private static final int MSG_EXCEPION=0x0002;

    private ListView listView;
    private List<Map<String, String>> data;

    private Handler handler=new Handler(){
        public void handleMessage(android.os.Message msg){
            switch (msg.what){
                case MSG_FILLDATA_FINISH:
                    SimpleAdapter adapter=new SimpleAdapter(
                            MainActivity.this,    //上下文
                            data,                 //数据源
                            R.layout.list_item,   //项布局
                            new String[]{"name","age"},          //要显示的数据在数据源中相应的key
                            new int[]{R.id.tvName, R.id.tvAge}); //要显示的数据在布局文件中相应的控件的id
                    listView.setAdapter(adapter);
                    break;
                case MSG_EXCEPION:
                    Toast.makeText(MainActivity.this,"数据出现异常无法显示", Toast.LENGTH_LONG).show();
                    break;
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//        try {
//            fillData2();   //填充数据源
//
            listView = (ListView) findViewById(R.id.listView1);
//
//            SimpleAdapter adapter = new SimpleAdapter(
//                    this,   //上下文
//                    data,   //数据源
//                    R.layout.list_item,                   //项布局，指向了一个layout文件（.xml文件）
//                    new String[]{"name", "age"},          //要显示的数据在数据源中相应的key
//                    new int[]{R.id.tvName, R.id.tvAge});  //要显示的数据在布局文件中相应的控件的id
//            listView.setAdapter(adapter);
//        } catch (XmlPullParserException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //fillData();  //填充数据源

        //使用线程，上面的代码段可以用，但性能上要差一些，所以要改为使用线程
        new Thread(){
            public void run(){
                try {
                    fillData2();
                    //发送通知
                    handler.sendEmptyMessage(MSG_FILLDATA_FINISH);
                } catch (XmlPullParserException e) {
                    handler.sendEmptyMessage(MSG_EXCEPION);
                    //e.printStackTrace();
                } catch (IOException e) {
                    handler.sendEmptyMessage(MSG_EXCEPION);
                    //e.printStackTrace();
                }
            }
        }.start();

    }

    /*
    解析XML， 填充数据源
     */
    private void fillData2() throws XmlPullParserException, IOException {
        List<Student> studentList = new ArrayList<Student>();
        Student stu = null;
        //解析XML
        XmlPullParser parser=getResources().getXml(R.xml.students);
        int evenType=parser.getEventType();
        while(evenType != XmlPullParser.END_DOCUMENT){
            switch(evenType){
                case XmlPullParser.START_DOCUMENT:
                    break;
                case XmlPullParser.START_TAG:
                    String name=parser.getName();
                    if(name.equals("student")){
                        stu=new Student();
                    }
                    if(name.equals("name")){
                        String stuName= parser.nextText();
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
            evenType=parser.next();
        }
        //填充数据
        data=new ArrayList<Map<String, String>>();
        for(Student s: studentList){
            Map<String, String> stu1=new HashMap<String, String>();  //一项
            stu1.put("name", s.getName());
            stu1.put("age", s.getAge());
            data.add(stu1);
        }
    }

    static class Student{
        private String name;
        private String age;

        public String getAge() {
            return age;
        }

        public String getName() {
            return name;
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

    private void fillData() {
        data = new ArrayList<Map<String,String>>();
        //第一条数据
        Map<String, String> stu1=new HashMap<String,String>();
        stu1.put("name", "张兰");
        stu1.put("age", "20");
        //添加到集合中
        data.add(stu1);

        //第二条数据
        Map<String, String> stu2=new HashMap<String,String >();
        stu2.put("name", "李四");
        stu2.put("age", "21");
        data.add(stu2);

    }
}
