package com.ramanora.stona.bean;

/**
 * Created by admin on 18-01-2017.
 */

public class Item {

    String MainListName;
    int MainListImage;

    public Item(String ItemName, int ItemImage)
    {
        this.MainListImage=ItemImage;
        this.MainListName=ItemName;
    }

    public String getMainListName() {
        return MainListName;
    }

    public void setMainListName(String mainListName) {
        MainListName = mainListName;
    }

    public int getMainListImage() {
        return MainListImage;
    }

    public void setMainListImage(int mainListImage) {
        MainListImage = mainListImage;
    }
}
