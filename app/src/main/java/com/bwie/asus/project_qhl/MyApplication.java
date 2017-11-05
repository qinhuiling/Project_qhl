package com.bwie.asus.project_qhl;

import android.app.Application;
import android.os.Environment;

import com.mob.MobSDK;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by ASUS on 2017/9/13.
 */

public class MyApplication extends Application {

    public MyApplication() {
    }
    protected String a() {
        return null;
    }

    protected String b() {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //短信验证com.mob.MobApplication
        MobSDK.init(this, this.a(), this.b());

        String path = Environment.getExternalStorageDirectory() + "/toutiao";
        File file = new File(path);
        //没有网的情况，首先回去内存找图片, 如果内存不存在次张图片的话，去sdcard，->去网络加载
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                //配置线程的优先级，图片加载为首
                .threadPriority(100)
                //指定由多个线程去加载图片
                .threadPoolSize(5)
                //指定要缓存多大尺寸的图片(内存缓存)
                .memoryCacheExtraOptions(200, 200)
                //配置sdcard缓存路径
                .diskCache(new UnlimitedDiskCache(file))
                //指定图片的名字，根据MD5算出来的一个串号(串号不会重名)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                //指明图片在内存占用多大空间(APP默认空间1/8)
                .memoryCacheSize(2 * 1024 * 1024)
                //指定图片在sdcard缓存多大(指的是占用空间)
                .diskCacheSize(50 * 1024 * 1024)
                .build();

        ImageLoader.getInstance().init(config);

        //推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

    }

    public static DisplayImageOptions options() {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
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
                //将图片变成圆形图片
                //.displayer(new CircleBitmapDisplayer())
                //将图片变成圆角矩形
                //.displayer(new RoundedBitmapDisplayer(20))
                .build();

        return options;
    }

}
