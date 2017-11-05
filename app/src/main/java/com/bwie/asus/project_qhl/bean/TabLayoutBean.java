package com.bwie.asus.project_qhl.bean;

/**
 * Created by ASUS on 2017/9/13.
 * 标题及接口
 */

public class TabLayoutBean {

    public TabLayoutBean(String title, String path) {
        this.title = title;
        this.path = path;
    }

    private String title;
    private String path;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "TabLayoutBean{" +
                "title='" + title + '\'' +
                ", path='" + path + '\'' +
                '}';
    }

}
