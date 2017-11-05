package com.bwie.asus.project_qhl.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bwie.asus.project_qhl.bean.JsonBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2017/9/14.
 */

public class MyManager {

    private final SQLiteDatabase db;
    private String table = "toutiao";

    public MyManager(Context context){
        MyHelper helper = new MyHelper(context);
        db = helper.getWritableDatabase();
    }

    public void insert(List<JsonBean.DataBean> list){
        for(int i=0;i<list.size();i++){
            ContentValues values = new ContentValues();
            JsonBean.DataBean bean = list.get(i);
            values.put("title",bean.getTitle());
            //values.put("url", bean.getMiddle_image().getUrl());

            db.insert(table,null,values);
        }
    }

    public List<JsonBean.DataBean> query(){
        List<JsonBean.DataBean> list = new ArrayList<>();
        Cursor cursor = db.query(table, null, null, null, null, null, null);
        while (cursor.moveToNext()){
            JsonBean.DataBean bean = new JsonBean.DataBean();
            bean.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            //bean.getMiddle_image().setUrl(cursor.getString(cursor.getColumnIndex("url")));
            list.add(bean);
        }
        return list;
    }

}
