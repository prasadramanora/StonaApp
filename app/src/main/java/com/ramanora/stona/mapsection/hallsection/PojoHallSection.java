package com.ramanora.stona.mapsection.hallsection;

/**
 * Created by admin on 9/14/2017.
 */

public class PojoHallSection {

    private String mtxt;
    private int mImg;


    public PojoHallSection(String mtxt, int mImg) {
        this.mtxt = mtxt;
        this.mImg = mImg;
    }

    public PojoHallSection() {

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
