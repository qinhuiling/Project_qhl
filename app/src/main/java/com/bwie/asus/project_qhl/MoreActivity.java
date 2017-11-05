package com.bwie.asus.project_qhl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
    }

    public void open(View view){
        finish();
    }

    public void phonelogin(View view){
        startActivity(new Intent(MoreActivity.this,NoteActivity.class));
    }

}
