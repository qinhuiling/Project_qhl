package com.bwie.asus.project_qhl.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bwie.asus.project_qhl.R;

import java.util.List;

/**
 * Created by ASUS on 2017/9/9.
 */

public class GridViewAdapter extends BaseAdapter{

    //定义上下文
    private Context context;
    //创建一个集合
    private List<String> list;
    //有参构造
    public GridViewAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    //集合的长度
    @Override
    public int getCount() {
        return list.size();
    }

    //集合的下标
    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //定义ViewHolder为空
        ViewHolder holder = null;
        //判断view是否为空
        if (view == null){
            //实例化ViewHolder
            holder = new ViewHolder();
            //加载布局
            view = View.inflate(context, R.layout.item_pindao,null);
            //查找控件
            holder.pindao_tv = view.findViewById(R.id.pindao_tv);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        //展示值
        holder.pindao_tv.setText(list.get(i));
        //返回view
        return view;
    }

    //定义ViewHolder
    class ViewHolder{
        TextView pindao_tv;
    }

}
