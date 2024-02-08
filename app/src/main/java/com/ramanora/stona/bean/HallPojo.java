package com.ramanora.stona.bean;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Owner on 09/10/2017.
 */

public class HallPojo implements Serializable {

    String hallname, hallId;
    ArrayList<HallExhibitorListPojo> mArrayListCompany;

    public HallPojo(){}


    public HallPojo(String hallname, ArrayList<HallExhibitorListPojo> mArrayListCompany) {
        this.hallname = hallname;
        this.mArrayListCompany = mArrayListCompany;
    }

    public HallPojo(String hallname, String hallId) {
        this.hallname = hallname;
        this.hallId = hallId;
    }

    public String getHallId() {
        return hallId;
    }

    public void setHallId(String hallId) {
        this.hallId = hallId;
    }

    public String getHallname() {
        return hallname;
    }

    public void setHallname(String hallname) {
        this.hallname = hallname;
    }

    public ArrayList<HallExhibitorListPojo> getmArrayListCompany() {
        return mArrayListCompany;
    }

    public void setmArrayListCompany(ArrayList<HallExhibitorListPojo> mArrayListCompany) {
        this.mArrayListCompany = mArrayListCompany;
    }
}
