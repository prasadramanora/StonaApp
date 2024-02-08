package com.ramanora.stona.bean;

import java.io.Serializable;


public class AZExhibitorListPojo implements Serializable {

    String mExhibitorID,mobileno, companyName, mcompanyImage, mhallno, firstname, address,pincode,state,fax,companyprofie,
            designation, city, country, email, website, phone, prodcut, newlauches,
            stallno, mobile, category, subcategory, productname, exilogo, exiproimg1, exiproimg2, exiproimg3, qrcodeimg,x,y,producting;

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public AZExhibitorListPojo() {
    }

    public AZExhibitorListPojo(String companyName) {


        this.companyName = companyName;
    }

    public AZExhibitorListPojo(String category, String subcategory) {
        this.subcategory = subcategory;

        this.category = category;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public AZExhibitorListPojo(String mobileno,String mExhibitorID, String companyName, String mhallno, String firstname, String designation, String city, String country, String email, String website, String phone, String prodcut, String newlauches) {

        this.mobileno = mobileno;
        this.mExhibitorID = mExhibitorID;
        this.companyName = companyName;
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

    public AZExhibitorListPojo(String mobileno, String mExhibitorID, String companyName, String mhallno, String address, String pincode, String state, String fax, String companyprofie, String city, String country, String email, String website, String phone, String stallno, String productname, String x, String y, String producting) {
        this.mobileno = mobileno;
        this.mExhibitorID = mExhibitorID;
        this.companyName = companyName;
        this.mhallno = mhallno;
        this.address = address;
        this.pincode = pincode;
        this.state = state;
        this.fax = fax;
        this.companyprofie = companyprofie;
        this.city = city;
        this.country = country;
        this.email = email;
        this.website = website;
        this.phone = phone;
        this.stallno = stallno;
        this.productname = productname;
        this.x = x;
        this.y = y;
        this.producting = producting;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCompanyprofie() {
        return companyprofie;
    }

    public void setCompanyprofie(String companyprofie) {
        this.companyprofie = companyprofie;
    }

    public String getProducting() {
        return producting;
    }

    public void setProducting(String producting) {
        this.producting = producting;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getExilogo() {
        return exilogo;
    }

    public void setExilogo(String exilogo) {
        this.exilogo = exilogo;
    }

    public String getExiproimg1() {
        return exiproimg1;
    }

    public void setExiproimg1(String exiproimg1) {
        this.exiproimg1 = exiproimg1;
    }

    public String getExiproimg2() {
        return exiproimg2;
    }

    public void setExiproimg2(String exiproimg2) {
        this.exiproimg2 = exiproimg2;
    }

    public String getExiproimg3() {
        return exiproimg3;
    }

    public void setExiproimg3(String exiproimg3) {
        this.exiproimg3 = exiproimg3;
    }

    public String getQrcodeimg() {
        return qrcodeimg;
    }

    public void setQrcodeimg(String qrcodeimg) {
        this.qrcodeimg = qrcodeimg;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStallno() {
        return stallno;
    }

    public void setStallno(String stallno) {
        this.stallno = stallno;
    }

    public String getmExhibitorID() {
        return mExhibitorID;
    }

    public void setmExhibitorID(String mExhibitorID) {
        this.mExhibitorID = mExhibitorID;
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

    public String getMhallno() {
        return mhallno;
    }

    public void setMhallno(String mhallno) {
        this.mhallno = mhallno;
    }

    public String getMcompanyImage() {
        return mcompanyImage;
    }

    public void setMcompanyImage(String mcompanyImage) {
        this.mcompanyImage = mcompanyImage;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


}
