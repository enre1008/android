package com.example.sunny_joy.toastdemo;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnToast, btnConToast, btnConToast2, btndisplayDialog, btndisplayDialog2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnToast=(Button)findViewById(R.id.btnToast);
        btnConToast=(Button)findViewById(R.id.btnConToast);
        btnConToast2=(Button)findViewById(R.id.btnConToast);
        btndisplayDialog=(Button)findViewById(R.id.btndisplayDialog);
        btndisplayDialog2=(Button)findViewById(R.id.btndisplayDialog2);
    }

    //显示对话框
    public void displayDialog(View view){
        switch (view.getId()){
            case R.id.btndisplayDialog:
                //显示经典对话框
                AlertDialog dlg=new AlertDialog.Builder(this)
                        .setIcon(R.drawable.peony)
                        .setTitle("Test")
                        .setMessage("这是一个测试对话框")
                        .setPositiveButton("确定",null)
                        .create();
                dlg.show();
                break;
            case R.id.btndisplayDialog2:
                //显示出有多个按钮的对话框
                new AlertDialog.Builder(this)
                        .setIcon(R.drawable.peony)
                        .setTitle("有多个按钮")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //当确定按钮点击时完成的工作
                                Toast.makeText(MainActivity.this, "确定按钮被点击", Toast.LENGTH_LONG).show();
                            }
                        } )
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "取消按钮被点击", Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNeutralButton("忽略",null)
                        .show();
                break;
        }

    }



    public void displayToast(View view) {
        switch (view.getId()){
            case R.id.btnToast:
                //使用静态方法makeText
                //三个参数：context 上下文，tex t消息内容，duration 持续时间长度
                //.show()方法，表示文这个消息需要显示出来
                Toast.makeText(this,"这是一个测试提示消息", Toast.LENGTH_LONG).show();
                break;
            case R.id.btnConToast:
                //1.构造函数来构建Toast
                Toast toast=new Toast(this);
                //2.创建要显示的View
                ImageView img=new ImageView(this);
                img.setImageResource(R.drawable.peony);
                //3.设置属性
                toast.setView(img);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.show();
                break;
            case R.id.btnConToast2:
                //显示处有图标的Toast
                //1。创建一个View
                //用静态方法from去拿LayoutInflater的对象，然后再用inflate方法解析布局
                View toastView = LayoutInflater.from(this).inflate(R.layout.toast_view, null);
                //2. 构造
                toast=new Toast(this);
                //3.设置
                toast.setView(toastView);
                toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM,0,0);
                toast.show();
                break;


        }
    }
}
