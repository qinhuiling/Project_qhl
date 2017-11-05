package com.bwie.asus.project_qhl;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView dao;
    private TextView tiaoguo;
    int i = 3;
    private int m;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            m = msg.what;
            dao.setText(m +"秒");
            if (m == 0){
                Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);
                startActivity(intent);
            }
            if (m < 0){
                finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        getSupportActionBar().hide();

        initView();
        initData();

    }

    private void initData() {

        new Thread(){
            @Override
            public void run() {
                super.run();
                while (i>0){
                    try {
                        sleep(1000);
                        i--;
                        handler.sendEmptyMessage(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        tiaoguo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = -1;
                Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initView() {
        dao = (TextView) findViewById(R.id.dao);
        tiaoguo = (TextView) findViewById(R.id.tiaoguo);
    }


}
