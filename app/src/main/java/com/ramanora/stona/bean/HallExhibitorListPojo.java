package com.ramanora.stona.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by admin on 10/14/2017.
 */

public class HallExhibitorListPojo implements Parcelable, Serializable {
    String mhallcolumnID, companyname,mcompanyImage ,mhallno,firstname,designation,city,country,email,website,phone,prodcut,newlauches;

       public String getMhallcolumnID() {
        return mhallcolumnID;
    }

    public void setMhallcolumnID(String mhallcolumnID) {
        this.mhallcolumnID = mhallcolumnID;
    }


    public HallExhibitorListPojo() {
    }

    public HallExhibitorListPojo(String mhallcolumnID, String companyname, String mhallno, String firstname, String designation, String city, String country, String email, String website, String phone, String prodcut, String newlauches) {
        this.mhallcolumnID = mhallcolumnID;
        this.companyname = companyname;
        this.mcompanyImage = mcompanyImage;
        this.mhallno = mhallno;
        this.firstname = firstname;
        this.designation = designation;
        this.city = city;
        this.country = country;
        this.email = email;
        this.website = website;
        this.phone = phone;
        this.prodcut = prodcut;
        this.newlauches = newlauches;
    }

    public String getMcompanyImage() {
        return mcompanyImage;
    }

    public void setMcompanyImage(String mcompanyImage) {
        this.mcompanyImage = mcompanyImage;
    }

    public String getMhallno() {
        return mhallno;
    }

    public void setMhallno(String mhallno) {
        this.mhallno = mhallno;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProdcut() {
        return prodcut;
    }

    public void setProdcut(String prodcut) {
        this.prodcut = prodcut;
    }

    public String getNewlauches() {
        return newlauches;
    }

    public void setNewlauches(String newlauches) {
        this.newlauches = newlauches;
    }

    public static Creator<HallExhibitorListPojo> getCREATOR() {
        return CREATOR;
    }

    public HallExhibitorListPojo(String companyname) {
        this.companyname = companyname;
    }

    protected HallExhibitorListPojo(Parcel in) {
        companyname = in.readString();
    }

    public static final Creator<HallExhibitorListPojo> CREATOR = new Creator<HallExhibitorListPojo>() {
        @Override
        public HallExhibitorListPojo createFromParcel(Parcel in) {
            return new HallExhibitorListPojo(in);
        }

        @Override
        public HallExhibitorListPojo[] newArray(int size) {
            return new HallExhibitorListPojo[size];
        }
    };

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(companyname);

    }
}
