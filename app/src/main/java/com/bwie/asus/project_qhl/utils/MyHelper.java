package com.bwie.asus.project_qhl.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ASUS on 2017/9/13.
 */

public class MyHelper extends SQLiteOpenHelper{

    public MyHelper(Context context) {
        super(context, "toutiao", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //sqLiteDatabase.execSQL("create table toutiao(id integer primary key autoincrement,title text,url text)");
        sqLiteDatabase.execSQL("create table toutiao(id integer primary key autoincrement,title text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
