package com.bwie.asus.project_qhl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<View> list = new ArrayList<>();
    private ViewPager view;
    int i = 0;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            view.setCurrentItem(what);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        initView();

    }

    private void initView() {
        view = (ViewPager) findViewById(R.id.view);
        View dao1 = View.inflate(this, R.layout.dao1, null);
        View dao2 = View.inflate(this, R.layout.dao2, null);
        View dao3 = View.inflate(this, R.layout.dao3, null);
        TextView jinru = dao3.findViewById(R.id.jinru);

        list.add(dao1);
        list.add(dao2);
        list.add(dao3);

        //关联适配器
        view.setAdapter(new MyPagerAdapter());
        new Thread(){
            @Override
            public void run() {
                super.run();
                while (true){
                    try {
                        sleep(1000);
                        i++;
                        handler.sendEmptyMessage(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }.start();

        SharedPreferences preferences = getSharedPreferences("a", MODE_PRIVATE);
        boolean falg = preferences.getBoolean("falg", false);
        if (falg){
            Intent intent = new Intent(MainActivity.this,SecondActivity.class);
            startActivity(intent);
        }else{
            preferences.edit().putBoolean("falg",true).commit();
        }

        jinru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });

    }

    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }

}
