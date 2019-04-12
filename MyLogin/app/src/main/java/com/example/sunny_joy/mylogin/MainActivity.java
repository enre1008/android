package com.example.sunny_joy.mylogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //声明控件
    private EditText etUsername, etPwd;
    private Button btnOK, btnCancel;
    private TextView tvResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //关联
        etUsername = (EditText)findViewById(R.id.etUsername);
        etPwd = (EditText)findViewById(R.id.etPwd);
        tvResult = (TextView)findViewById(R.id.tvResult);

        btnOK = (Button)findViewById(R.id.btnOK);
        btnCancel = (Button)findViewById(R.id.btnCancel);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //当点击确定按钮需完成当工作
                //在tvResult中显示用户名与密码

                //得到用户输入
                String username = etUsername.getText().toString();
                String pwd = etPwd.getText().toString();
                tvResult.setText("用户名： " + username + " 密码：" + pwd);

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //当点击取消按钮完成当工作
                //清空控件内容
                etUsername.setText("");
                etPwd.setText("");
                tvResult.setText("");

            }
        });


    }
}
