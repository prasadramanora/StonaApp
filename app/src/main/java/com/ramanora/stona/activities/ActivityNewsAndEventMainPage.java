package com.ramanora.stona.activities;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.ramanora.stona.R;
import com.ramanora.stona.database.DataBaseHandler;
import com.ramanora.stona.fragments.FragmentEventSchedule;
import com.ramanora.stona.fragments.FragmentNewsFeed;
import com.ramanora.stona.utils.UrlConstants;

import java.util.ArrayList;
import java.util.List;


public class ActivityNewsAndEventMainPage extends AppCompatActivity {

   /* private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private DataBaseHandler databaseHelper;
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity_news_and_event_mainpage);

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(UrlConstants.URL_SOCIAL_FACEBOOK));
        startActivity(intent);

    }
/*Intent intent=getIntent();
        if(intent.hasExtra("msg")){
            Log.d("activitynewstest", "onCreate: "+intent.hasExtra("msg"));
        }
        // toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);
        databaseHelper = new DataBaseHandler(ActivityNewsAndEventMainPage.this);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        databaseHelper.clearNotificationCount();
        viewPager = (ViewPager) findViewById(R.id.viewpagernewandupdates);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#f47c0b"));
        tabLayout.setSelectedTabIndicatorHeight((int) (4 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#1f3b7a"), Color.parseColor("#1f3b7a"));
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentNewsFeed(), "Industry" + "\n" + "News ");
        adapter.addFragment(new FragmentEventSchedule(), "Event " + "\n" + "Schedule");
        // adapter.addFragment(new ThreeFragment(), "THREE");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

   *//* @Override
    public void onBackPressed() {
        databaseHelper = new DataBaseHandler(this);
        databaseHelper.clearNotificationCount();

    }*/
}
