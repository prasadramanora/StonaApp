package com.ramanora.stona.tiker;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ramanora.stona.R;


public class ImagePagerAdapter extends FragmentPagerAdapter {

    int [] resIds = new int[]{R.drawable.addstona1, R.drawable.addstona2, R.drawable.addstona3};

    public ImagePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return ImageFragment.newInstance(resIds[i]);
    }

    @Override
    public int getCount() {
        return resIds.length;
    }


}