package com.example.sunny_joy.gridviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sunny-joy on 2018/6/6.
 */

public class Demo2Activity extends Activity {
    private Spinner spnGrade, spnStudent;
    private List<String> gradeList=new ArrayList<String>();
    private Map<String, List<String>> stuMap=new HashMap<String, List<String>>();
    private List<String> selectedStudent=new ArrayList<String>();
    private ArrayAdapter<String> stuAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2);

        spnGrade=(Spinner)findViewById(R.id.spnGrade);
        spnStudent=(Spinner)findViewById(R.id.spnStudent);

        fillData();

        spnGrade.setAdapter(new ArrayAdapter<String>(
                Demo2Activity.this, android.R.layout.simple_list_item_1, android.R.id.text1, gradeList
        ));

        stuAdapter=new ArrayAdapter<String>(
                Demo2Activity.this, android.R.layout.simple_list_item_1, android.R.id.text1, selectedStudent
        );

        spnStudent.setAdapter(stuAdapter);

        spnGrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedStudent.clear();
                String selectedGrade=gradeList.get(position);
                selectedStudent.addAll(stuMap.get(selectedGrade));
                stuAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void fillData(){
        gradeList.add("android班");
        gradeList.add("嵌入式班");

        List<String> studentAndroid=new ArrayList<String>();
        studentAndroid.add("张三");
        studentAndroid.add("李四");

        List<String> studentAmr=new ArrayList<String>();
        studentAmr.add("王五");
        studentAmr.add("赵六");

        stuMap.put(gradeList.get(0), studentAndroid);
        stuMap.put(gradeList.get(1), studentAmr);

    }

}
