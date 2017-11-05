package com.bwie.asus.project_qhl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.smssdk.gui.RegisterPage;

public class NoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        RegisterPage registerPage = new RegisterPage();
        registerPage.show(NoteActivity.this);

        finish();

    }



}
