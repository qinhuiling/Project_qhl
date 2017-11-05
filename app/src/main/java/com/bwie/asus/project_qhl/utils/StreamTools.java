package com.bwie.asus.project_qhl.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by ASUS on 2017/9/13.
 */

public class StreamTools {
    public static String readNetWork(InputStream is) {
        try {
            //创建输出流,用以输出字符串
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //字节缓冲区
            byte[] buffer = new byte[1024];
            //每一次读入多少长度
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            //关闭流
            is.close();
            baos.close();
            //返回baos.toString()
            return baos.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
