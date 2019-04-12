package com.example.sunny.dbcreateandupdate;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.sunny.dbcreateandupdate.db.MyOpenHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void test(View view) {
        MyOpenHelper helper=new MyOpenHelper(this, "test.db");
        SQLiteDatabase db=helper.getReadableDatabase();
        String sql="insert into test(name)values('aaa')";
        db.execSQL(sql);
    }
}
