package com.ramanora.stona.bean;

/**
 * Created by admin on 9/14/2017.
 */

public class PojoHomePojo {
String count;
    private String mtxt, Notification;
    private int mImg;


    public PojoHomePojo(String mtxt, int mImg) {
        this.mtxt = mtxt;
        this.mImg = mImg;
    }

    public PojoHomePojo() {

    }

    public String getNotification() {
        return Notification;
    }

    public void setNotification(String notification) {
        Notification = notification;
    }
    public String getMtxt() {
        return mtxt;
    }

    public void setMtxt(String mtxt) {
        this.mtxt = mtxt;
    }

    public int getmImg() {
        return mImg;
    }

    public void setmImg(int mImg) {
        this.mImg = mImg;
    }
}
