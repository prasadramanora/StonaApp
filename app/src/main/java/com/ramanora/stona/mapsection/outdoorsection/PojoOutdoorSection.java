package com.ramanora.stona.mapsection.outdoorsection;

/**
 * Created by admin on 9/14/2017.
 */

public class PojoOutdoorSection {

    private String mtxt;
    private int mImg;


    public PojoOutdoorSection(String mtxt, int mImg) {
        this.mtxt = mtxt;
        this.mImg = mImg;
    }

    public PojoOutdoorSection() {

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
