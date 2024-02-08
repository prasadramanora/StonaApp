package com.ramanora.stona.tiker;


import android.os.Handler;
import android.support.v4.view.ViewPager;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PagerScheduleProxy {

    int mCurrentItem = 1;
    int mInterval = 3000;

    ScheduledExecutorService mScheduledExecutorService;
    ViewPager viewPager;

    public PagerScheduleProxy(ViewPager viewPager, int interval) {
        this.viewPager = viewPager;
        this.mInterval = interval;
    }

    private class ScrollTask implements Runnable {

        public void run() {
            mCurrentItem = (mCurrentItem + 1) % viewPager.getAdapter().getCount();
            mHandler.obtainMessage().sendToTarget();
        }
    }


      private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            viewPager.setCurrentItem(mCurrentItem);
        }
    };

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            if(mScheduledExecutorService != null) mScheduledExecutorService.shutdownNow();
            mScheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
            mScheduledExecutorService .schedule(new ScrollTask(), mInterval, TimeUnit.SECONDS);
            mCurrentItem = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public ViewPager.OnPageChangeListener getOnPageChangeListener() {
        return onPageChangeListener;
    }



    public void onStart(){
        mScheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        mScheduledExecutorService .schedule(new ScrollTask(), mInterval,TimeUnit.SECONDS);
    }


    public void onStop(){

        if(mScheduledExecutorService!=null) mScheduledExecutorService.shutdownNow();
    }


}
