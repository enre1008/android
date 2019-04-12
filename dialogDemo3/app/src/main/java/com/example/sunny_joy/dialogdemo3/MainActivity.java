package com.example.sunny_joy.dialogdemo3;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.RecoverySystem;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Dialog customDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //创建Dialog对象
        createCustomeDialog();
    }

    /*
    创建Dialog对象
     */
    private void createCustomeDialog(){
        LayoutInflater inflater=getLayoutInflater();
        final View customView=inflater.inflate(R.layout.custom_view2,null);
        Button btnOk=customView.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭对话框
                customDialog.dismiss();
            }
        });

        customDialog=new Dialog(this);
        customDialog.setTitle("自定义");
        customDialog.setContentView(customView);
    }

    public void displayCustomDialog2(View view){
        customDialog.show();
    }



    public void displayCustomDialog(View view){
        LayoutInflater inflater=getLayoutInflater();
        View customView=inflater.inflate(R.layout.custom_view,null);
        final EditText etUsername=(EditText)customView.findViewById(R.id.etUsername);
        final EditText etPassword=(EditText)customView.findViewById(R.id.etPassword);

        //使用AlertDialog显示自定义视图
        new AlertDialog.Builder(this)
                .setTitle("自定义")
                .setView(customView)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String username=etUsername.getText().toString();
                        String password=etPassword.getText().toString();
                        Toast.makeText(MainActivity.this, username+", "+password, Toast.LENGTH_LONG).show();
                    }
                })
                .show();
    }

    public void displayProgress(View view) {
        DialogFragment dlg = new DialogFragment();
        dlg.setMenuVisibility(true);
        dlg.setShowsDialog(true);
        dlg.getShowsDialog();
    }
}
