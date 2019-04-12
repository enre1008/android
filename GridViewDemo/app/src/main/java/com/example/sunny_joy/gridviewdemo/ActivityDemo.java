package com.example.sunny_joy.gridviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunny-joy on 2018/6/5.
 */

public class ActivityDemo extends Activity implements View.OnClickListener{
    private Button btnAdd;
    private EditText etStudentName;
    private GridView gridStudent;

    ArrayList<String> studentNames=new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        initControl();

        adapter=new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                studentNames
        );
        gridStudent.setAdapter(adapter);
    }

    private void initControl(){
        btnAdd=(Button)findViewById(R.id.btnAdd);
        etStudentName=(EditText)findViewById(R.id.editText1);
        gridStudent=(GridView)findViewById(R.id.gridView1);

        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String name=etStudentName.getText().toString().trim();
        studentNames.add(name);

        adapter.notifyDataSetChanged();
    }
}
