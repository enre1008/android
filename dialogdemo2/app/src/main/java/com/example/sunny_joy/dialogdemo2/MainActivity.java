package com.example.sunny_joy.dialogdemo2;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private AlertDialog dlg;
    private AlertDialog dlg2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createSingleDidalog();
        createMultiDialog();
    }

    private void createMultiDialog(){
        final String[] items={"AAA","BBB","CCC","DDD"};
        dlg2=new AlertDialog.Builder(this)
                .setIcon(R.drawable.tulip)
                .setTitle("多选列表框")
                .setMultiChoiceItems(items, new boolean[]{false, true, false, false}, new DialogInterface.OnMultiChoiceClickListener() {
                    /*
                    which 点击项的索引
                    isChecked 点击项是否被选中
                     */
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        Log.i("TEST", which+", "+isChecked);
                    }
                })
                .setPositiveButton("确定", null)
                .create();
    }


    public void displayMultiDialog(View view){
        dlg2.show();
    }

    /*
    创建一个对话框
     */
    private void createSingleDidalog(){
        final String[] items={"AAA","BBB","CCC","DDD"};
        dlg=new AlertDialog.Builder(this)
                .setIcon(R.drawable.tulip)
                .setTitle("列表对话框")
                .setSingleChoiceItems(items, 0, null)
                .setPositiveButton("确定",null)
                .create();

    }

    public void displayRadioDialog(View view){
        //显示单选按钮的对话框
                dlg.show();
    }


    public void displayListDialog(View view){
        final String[] items={"AAA","BBB","CCC","DDD"};
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.tulip)
                .setTitle("列表对话框")
                .setItems(items, new DialogInterface.OnClickListener() {
                    /*
                    dialog 是发出事件的对话框
                    which 是列表项的索引
                     */
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("TEST", which+" ");
                        Toast.makeText(MainActivity.this, items[which], Toast.LENGTH_LONG).show();
                    }
                })
                .show();
    }

}
