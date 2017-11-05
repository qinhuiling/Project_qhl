package com.bwie.asus.project_qhl;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.math.BigDecimal;

import androidkun.com.versionupdatelibrary.entity.VersionUpdateConfig;
import cn.jpush.android.api.JPushInterface;

public class SetActivity extends AppCompatActivity {

    private TextView clear;
    private TextView dangqian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        clear = (TextView) findViewById(R.id.clear);
        dangqian = (TextView) findViewById(R.id.dangqian);
        initclear();

    }

    public void open(View view) {
        finish();
    }

    public void tuisong(View view) {
        SharedPreferences preferences = getSharedPreferences("a", MODE_PRIVATE);
        boolean falg = preferences.getBoolean("falg", false);
        if (falg) {
            preferences.edit().putBoolean("falg", false).commit();
            Toast.makeText(this, "接收推送", Toast.LENGTH_SHORT).show();
            JPushInterface.resumePush(getApplicationContext());
        } else {
            preferences.edit().putBoolean("falg", true).commit();
            Toast.makeText(this, "不接收推送", Toast.LENGTH_SHORT).show();
            JPushInterface.stopPush(getApplicationContext());
        }
    }

    public void set(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("发现新版本");
        builder.setMessage("优化了评论的互动体验");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("升级", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                VersionUpdateConfig.getInstance()//获取配置实例
                        .setContext(SetActivity.this)//设置上下文
                        .setDownLoadURL("http://gdown.baidu.com/data/wisegame/ec96b542385ffa41/jinritoutiao_608.apk")//设置文件下载链接
//                .setFileSavePath(savePath)//设置文件保存路径（可不设置）
//                .setNotificationIconRes(R.mipmap.app_icon)//设置通知图标
//                .setNotificationSmallIconRes(R.mipmap.app_icon_small)//设置通知小图标
                        .setNotificationTitle("版本升级Demo")//设置通知标题
                        .startDownLoad();//开始下载
            }
        });
        builder.show();
    }


    private void initclear() {

        //计算当前缓存
        try {
            String size = getTotalCacheSize();
            dangqian.setText("当前缓存" + size);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //清理缓存
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(SetActivity.this);
                builder.setTitle("提示");
                builder.setMessage("确定要删除所有缓存吗？");
                builder.setNegativeButton("取消", null);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        clearAllCache(SetActivity.this);
                        String size = null;
                        try {
                            size = getTotalCacheSize();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        dangqian.setText("当前缓存:" + size);
                    }
                });
                builder.show();

            }
        });

    }

    /**
     * 计算app的缓存大小其实计算的是 getCacheDir()这个file和getExternalCacheDir()这个file大小的和
     */
    public String getTotalCacheSize() throws Exception {
        long cacheSize = getFolderSize(this.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheSize += getFolderSize(this.getExternalCacheDir());
        }
        return getFormatSize(cacheSize);
    }

    /**
     * 计算文件夹的大小
     */
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                // 如果下面还有文件
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 清理app的缓存 其实是清除的getCacheDir 和getExternalCacheDir这两个文件
     *
     * @param context
     */
    public static void clearAllCache(Context context) {
        deleteDir(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            deleteDir(context.getExternalCacheDir());
        }
    }

    /**
     * 格式化得到的总大小 默认是byte,  然后根据传入的大小,自动转化成合适的大小单位
     *
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
//            return size + "Byte";
            return "0K";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }

    /**
     * 删除一个文件夹
     *
     * @param dir
     * @return
     */
    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

}
