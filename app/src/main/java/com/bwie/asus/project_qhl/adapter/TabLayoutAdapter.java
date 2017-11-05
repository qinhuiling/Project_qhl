package com.bwie.asus.project_qhl.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.asus.project_qhl.MyApplication;
import com.bwie.asus.project_qhl.R;
import com.bwie.asus.project_qhl.bean.JsonBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by ASUS on 2017/9/13.
 */

public class TabLayoutAdapter extends BaseAdapter {
    //定义上下文
    private Context context;
    //创建一个集合
    private List<JsonBean.DataBean> list;

    private static final int type1 = 0;
    private static final int type2 = 1;
    private final DisplayImageOptions options;
    private static final int type3 = 2;
    private static final int type4 = 3;

    //有参构造
    public TabLayoutAdapter(Context context, List<JsonBean.DataBean> list) {
        this.context = context;
        this.list = list;

        options = new DisplayImageOptions.Builder()
                //配置是否进行内存缓存
                .cacheInMemory(true)
                //配置是否对图片进行sdcard缓存
                .cacheOnDisk(true)
                //当图片地址为空的时候，指定一个默认的图片
                .showImageForEmptyUri(R.mipmap.ic_empty)
                //当图片加载失败的时候显示的默认图片
                .showImageOnFail(R.mipmap.ic_error)
                //当图片正在加载的时候会显示的图片
                .showImageOnLoading(R.mipmap.loading)
                .build();

    }

    public void loadMore(List<JsonBean.DataBean> list1,boolean falg){
        for (JsonBean.DataBean bean : list1){
            if (falg){
                list.add(bean);
            }else{
                list.add(0,bean);
            }
        }
        notifyDataSetChanged();
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
    public int getItemViewType(int position) {

        if (list.get(position).getVideo_detail_info()==null){
            if(list.get(position).getImage_list()==null || "".equals(list.get(position).getImage_list())){
                return type1;
            }else if(list.get(position).getImage_list().size() == 2){
                return type2;
            }else{
                return type3;
            }
        }else{
            return type4;
        }

    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        int type = getItemViewType(i);

        //定义ViewHolder为空
        ViewHolder holder = null;
        //判断view是否为空
        if (view == null) {
            //实例化ViewHolder
            holder = new ViewHolder();

            switch (type){
                case 0:
                    //加载布局
                    view = View.inflate(context, R.layout.item01, null);
                    //查找控件
                    holder.tv1 = view.findViewById(R.id.tv1);
                    break;
                case 1:
                    //加载布局
                    view = View.inflate(context, R.layout.item02, null);
                    //查找控件
                    holder.tv2 = view.findViewById(R.id.tv2);
                    holder.iv2 = view.findViewById(R.id.iv2);
                    break;
                case 2:
                    //加载布局
                    view = View.inflate(context, R.layout.item03, null);
                    //查找控件
                    holder.tv3 = view.findViewById(R.id.tv3);
                    holder.iv1 = view.findViewById(R.id.iv3_img1);
                    holder.iv2 = view.findViewById(R.id.iv3_img2);
                    holder.iv3 = view.findViewById(R.id.iv3_img3);
                    break;
                case 3:
                    //加载布局
                    view = View.inflate(context, R.layout.item04, null);
                    //查找控件
                    holder.tv4 = view.findViewById(R.id.tv4);
                    holder.iv4 = view.findViewById(R.id.iv4);
                    break;

            }

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        switch (type){
            case 0:
                //展示值
                holder.tv1.setText(list.get(i).getTitle());
                break;
            case 1:
                //展示值
                holder.tv2.setText(list.get(i).getTitle());

                ImageLoader.getInstance().displayImage(list.get(i).getMiddle_image().getUrl(), holder.iv2, options);

                break;
            case 2:
                //展示值
                holder.tv3.setText(list.get(i).getTitle());
                if (list.get(i).getImage_list().size() == 3){
                    ImageLoader.getInstance().displayImage(list.get(i).getImage_list().get(0).getUrl(),holder.iv1, MyApplication.options());
                    ImageLoader.getInstance().displayImage(list.get(i).getImage_list().get(1).getUrl(),holder.iv2, MyApplication.options());
                    ImageLoader.getInstance().displayImage(list.get(i).getImage_list().get(2).getUrl(),holder.iv3, MyApplication.options());
                }
                break;
            case 3:
                //展示值
                holder.tv4.setText(list.get(i).getTitle());
                ImageLoader.getInstance().displayImage(list.get(i).getVideo_detail_info().getDetail_video_large_image().getUrl(),holder.iv4, MyApplication.options());
                break;
        }
        //返回view
        return view;
    }

    //定义ViewHolder
    class ViewHolder {
        TextView tv1,tv2,tv3,tv4;
        ImageView iv1,iv2,iv3,iv4;
    }
}
