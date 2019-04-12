package com.example.sunny.dbcreateandupdate.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyOpenHelper extends SQLiteOpenHelper{
    /*
    context, name 数据库文件名, factory如果维null使用默认方式,version版本，只能升不能降
     */
    public static final int VERSION=3;
    private static final String CREATE_TABLE_TEST="create table Test(_id intger primary key autoincrement, name)";
    public MyOpenHelper(Context context, String name) {
        super(context, name, null, VERSION);
    }
    public MyOpenHelper(Context context, String name, int version) {
        super(context, name, null, VERSION);
    }

    /*
        当数据库文件不存在，创建数据库文件，并且第一次使用时调用onCreate()
         */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("TEST1", "onCreate");
        //创建表只需一次
        db.execSQL(CREATE_TABLE_TEST);
    }

    /*
    版本更新时
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.i("TEST", "onUpdagrade:oldVersion--- "+oldVersion+"newVersion---"+newVersion);
    }
}
