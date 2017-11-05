package com.bwie.asus.project_qhl.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.bwie.asus.project_qhl.FifthActivity;
import com.bwie.asus.project_qhl.R;
import com.bwie.asus.project_qhl.adapter.TabLayoutAdapter;
import com.bwie.asus.project_qhl.bean.JsonBean;
import com.bwie.asus.project_qhl.utils.StreamTools;
import com.google.gson.Gson;
import com.limxing.xlistview.view.XListView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by ASUS on 2017/9/13.
 */

public class Fragment01 extends Fragment implements XListView.IXListViewListener {

    private XListView xlv;
    private TabLayoutAdapter adapter;
    boolean falg;
    int num = 10;
    private View view;
    private String url;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //加载布局
        view = inflater.inflate(R.layout.tabfragment, container, false);

        url = getArguments().getString("url");

        //查找控件
        xlv = view.findViewById(R.id.xlv);

        xlv.setPullLoadEnable(true);
        //设置上拉，下滑刷新
        xlv.setXListViewListener(this);

        xlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                JsonBean.DataBean bean = (JsonBean.DataBean) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(getActivity(), FifthActivity.class);
                intent.putExtra("url",bean.getUrl());
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        forGet(url);
    }

    //定义方法
    public void forGet(String url) {
        //异步加载
        new AsyncTask<String, Void, String>() {
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s == null) {
                    return;
                }

                //实例化Gson
                Gson gson = new Gson();
                //解析字符串
                JsonBean jsonBean = gson.fromJson(s, JsonBean.class);
                //将解析出来的值存入集合
                List<JsonBean.DataBean> data = jsonBean.getData();

                //判断适配器是否为空
                if (adapter == null) {

//                    MyManager manager = new MyManager(getActivity());
//                    manager.insert(data);
//                    List<JsonBean.DataBean> query = manager.query();

                    //创建适配器
                    adapter = new TabLayoutAdapter(getActivity(), data);
                    //关联数据
                    xlv.setAdapter(adapter);

                } else {
                    adapter.loadMore(data, falg);
                }

            }

            @Override
            protected String doInBackground(String... strings) {
                try {
                    //创建HttpURLConnection并敲回车
                    HttpURLConnection conn = (HttpURLConnection) new URL(strings[0]).openConnection();
                    //设置请求方式
                    conn.setRequestMethod("GET");
                    //设置服务器连接时间
                    conn.setConnectTimeout(5000);
                    //设置网络数据连接时间
                    conn.setReadTimeout(5000);
                    //等待服务器响应并判断服务器是否响应成功
                    if (conn.getResponseCode() == 200) {
                        //得到数据
                        InputStream is = conn.getInputStream();
                        //返回得到的数据
                        return StreamTools.readNetWork(is);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(url);

    }

    public static Fragment newInstance(String url) {
        Fragment01 fragment01 = new Fragment01();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        fragment01.setArguments(bundle);
        return fragment01;
    }

    @Override
    public void onRefresh() {
        falg = false;
        ++num;
        forGet(url);
        xlv.stopRefresh(true);
    }

    @Override
    public void onLoadMore() {
        falg = true;
        ++num;
        forGet(url);
        xlv.stopLoadMore();
    }

}