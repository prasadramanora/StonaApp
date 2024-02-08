package com.ramanora.stona.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.ramanora.stona.R;
import com.ramanora.stona.activities.ActivityExhibitiorlistTab;
import com.ramanora.stona.adapter.AZGridViewAdapter;
import com.ramanora.stona.bean.AZExhibitorListPojo;
import com.ramanora.stona.bean.GridAZPojo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExhibitorListGridViewFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {


    //PagerScheduleProxy pagerScheduleProxy;
    SliderLayout sliderLayout;
    HashMap<String, Integer> Hash_file_maps;

    String companyname;

    private final String data[] = {
            "Products",
              "Floor Plan",
            //  "New Launches",
            "Call us",
            "About us",
    };
    private final int[] imageId = {
            R.drawable.stonaproduct,
            R.drawable.mapstona,
            //R.drawable.stonanewlauncheswt,
            R.drawable.stonacalluswt,
            R.drawable.stonaaboutwt

    };

    ArrayList<AZExhibitorListPojo> arrayListcompany = new ArrayList<AZExhibitorListPojo>();


    public ExhibitorListGridViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.exhibitor_list_grid_view, null);

        TextView textView = (TextView) view.findViewById(R.id.companyNameGridview);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


/*
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        CircleIndicator circleIndicator = (CircleIndicator) view.findViewById(R.id.indicator);
        ImagePagerAdapter imagePagerAdapter = new ImagePagerAdapter(getFragmentManager());R
        viewPager.setAdapter(imagePagerAdapter);

        circleIndicator.setViewPager(viewPager);
        pagerScheduleProxy = new PagerScheduleProxy(viewPager, 2); //param 2 is interval(s)
        circleIndicator.setOnPageChangeListener(pagerScheduleProxy.getOnPageChangeListener());*/

        //Data come from AZExhibitorAdapter
        Bundle bundle = getArguments();
        AZExhibitorListPojo azExhibitorListPojo = (AZExhibitorListPojo) bundle.getSerializable("AZData");
        String CompanyName = azExhibitorListPojo.getCompanyName();

        System.out.println("test1" + azExhibitorListPojo.getMhallno());
        arrayListcompany.add(azExhibitorListPojo);
        textView.setText(CompanyName);

        hideKeyboard(getActivity());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_grid_viewaz);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<GridAZPojo> mArrayListGrid = prepareData1();
        AZGridViewAdapter adapter = new AZGridViewAdapter(mArrayListGrid, arrayListcompany, getActivity());
        recyclerView.setAdapter(adapter);


        return view;


    }

    private ArrayList<GridAZPojo> prepareData1(){

        ArrayList<GridAZPojo> mArrayListGrid= new ArrayList<>();
        for(int i=0;i<data.length;i++){
            GridAZPojo pojo = new GridAZPojo();
            pojo.setMtxt(data[i]);
            pojo.setmImg(imageId[i]);
            mArrayListGrid.add(pojo);
        }
        return mArrayListGrid;

    }

    public static void hideKeyboard(Context ctx) {
        InputMethodManager inputManager = (InputMethodManager) ctx
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View v = ((ActivityExhibitiorlistTab) ctx).getCurrentFocus();
        if (v == null)
            return;

        inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    //image slider

    @Override
    public void onStop() {
try {
    sliderLayout.stopAutoCycle();
}catch (Exception e)
{
    e.printStackTrace();
}

        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

        //     Toast.makeText(this,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {

        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }




}
