package com.ramanora.stona.bean;

import java.io.Serializable;

/**
 * Created by pragati on 28/8/17.
 */

public class NewsArticlesPojo implements Serializable{

    String mTitle,mSummary,mDescription,mwebUrl,mNEwsimage,mPublishAt,mCompanyName,mImageOfCompany,likes;

    private int count;


    public NewsArticlesPojo() {
    }

    public NewsArticlesPojo(String mTitle, String mSummary, String mDescription, String mNEwsimage, String mPublishAt, String mCompanyName, String mImageOfCompany) {
        this.mTitle = mTitle;
        this.mSummary = mSummary;
        this.mDescription = mDescription;
       // this.mwebUrl = mwebUrl;
        this.mNEwsimage = mNEwsimage;
        this.mPublishAt = mPublishAt;
        this.mCompanyName = mCompanyName;
        this.mImageOfCompany = mImageOfCompany;
        //this.likes = likes;
    }


    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmSummary() {
        return mSummary;
    }

    public void setmSummary(String mSummary) {
        this.mSummary = mSummary;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getMwebUrl() {
        return mwebUrl;
    }

    public void setMwebUrl(String mwebUrl) {
        this.mwebUrl = mwebUrl;
    }

    public String getmNEwsimage() {
        return mNEwsimage;
    }

    public void setmNEwsimage(String mNEwsimage) {
        this.mNEwsimage = mNEwsimage;
    }

    public String getmPublishAt() {
        return mPublishAt;
    }

    public void setmPublishAt(String mPublishAt) {
        this.mPublishAt = mPublishAt;
    }

    public String getmCompanyName() {
        return mCompanyName;
    }

    public void setmCompanyName(String mCompanyName) {
        this.mCompanyName = mCompanyName;
    }

    public String getmImageOfCompany() {
        return mImageOfCompany;
    }

    public void setmImageOfCompany(String mImageOfCompany) {
        this.mImageOfCompany = mImageOfCompany;
    }


}
