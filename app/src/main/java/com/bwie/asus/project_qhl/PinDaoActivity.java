package com.bwie.asus.project_qhl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.bwie.asus.project_qhl.adapter.GridViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class PinDaoActivity extends AppCompatActivity {

    private GridView pindao_gv1;
    private GridView pindao_gv2;
    private List<String> pin1;
    private List<String> pin2;
    private ImageView pindao_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_dao);

        initView();
        initData();
        initManager();

    }

    private void initView() {
        pindao_gv1 = (GridView) findViewById(R.id.pindao_gv1);
        pindao_gv2 = (GridView) findViewById(R.id.pindao_gv2);
        pindao_iv = (ImageView) findViewById(R.id.pindao_iv);
    }

    private void initData() {
        pin1 = new ArrayList<>();
        pin2 = new ArrayList<>();
        pin1.add("推荐");
        pin1.add("热点");
        pin1.add("本地");
        pin1.add("视频");
        pin1.add("社会");
        pin1.add("娱乐");
        pin1.add("科技");
        pin1.add("汽车");
        pin1.add("体育");
        pin1.add("财经");
        pin1.add("军事");
        pin1.add("国际");
        pin1.add("段子");
        pin1.add("趣图");
        pin1.add("健康");
        pin1.add("美女");

        pin2.add("新闻");
        pin2.add("人性");
        pin2.add("美食");

        final GridViewAdapter adapter1 = new GridViewAdapter(this,pin1);
        pindao_gv1.setAdapter(adapter1);
        final GridViewAdapter adapter2 = new GridViewAdapter(this,pin2);
        pindao_gv2.setAdapter(adapter2);

        pindao_gv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String s1 = pin1.get(i);
                pin1.remove(s1);
                adapter1.notifyDataSetChanged();
                pin2.add(s1);
                adapter2.notifyDataSetChanged();
            }
        });

        pindao_gv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String s2 = pin2.get(i);
                pin2.remove(s2);
                adapter2.notifyDataSetChanged();
                pin1.add(s2);
                adapter1.notifyDataSetChanged();
            }
        });

        pindao_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void initManager() {

    }

}
