package com.bwie.asus.project_qhl.bean;

/**
 * Created by 秦惠玲 on 2017/9/13.
 * 左侧滑工具类
 */

public class LeftListViewBean {

    public LeftListViewBean(int leftImage, String leftText) {
        this.leftImage = leftImage;
        this.leftText = leftText;
    }

    private int leftImage;
    private String leftText;

    public int getLeftImage() {
        return leftImage;
    }

    public void setLeftImage(int leftImage) {
        this.leftImage = leftImage;
    }

    public String getLeftText() {
        return leftText;
    }

    public void setLeftText(String leftText) {
        this.leftText = leftText;
    }

    @Override
    public String toString() {
        return "LeftListViewBean{" +
                "leftImage=" + leftImage +
                ", leftText='" + leftText + '\'' +
                '}';
    }

}
