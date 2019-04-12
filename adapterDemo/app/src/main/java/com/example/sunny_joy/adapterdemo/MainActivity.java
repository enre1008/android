package com.example.sunny_joy.adapterdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    //数据源
    private  String[] data={"上海","北京","广州","深圳"};
    //控件
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=(ListView)findViewById(R.id.listView1);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //parent, 指listView；view，指每一项item；position，指现在点击的项的索引；
                Log.i("TEST", position+",");
                Toast.makeText(MainActivity.this, "你所选的城市是："+data[position], Toast.LENGTH_LONG).show();
            }
        });
        //适配器
//        ArrayAdapter<String> adapter=new ArrayAdapter<String>(
//                this,  //上下文，表示为希望显示在哪里，希望显示在为自身的activity中，故写this
//                R.layout.list_item, //@LayoutRes int resource, 每一项数据显示时的项布局
//                R.id.text1,         //@IdRes int textViewResourceId, 数据要显示在控件的Id
//                data);  //数据源
        //使用系统定义的布局
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,  //系统中已经定义过的布局文件
                android.R.id.text1,  //布局文件中的一个textview，它的id为text
                data);


        //设置适配器
        listView.setAdapter(adapter);

    }
}
