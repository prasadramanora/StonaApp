package com.ramanora.stona.fragments;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import com.ramanora.stona.R;
import com.ramanora.stona.activities.ActivityExhibitiorlistTab;
import com.ramanora.stona.adapter.AZExhibitorlistAdapter;
import com.ramanora.stona.bean.AZExhibitorListPojo;
import com.ramanora.stona.database.DatabaseAccess;
import java.util.ArrayList;

public class AZFragment extends Fragment  {

    RecyclerView recyclerView;
    LinearLayoutManager manager;
    SearchView editTextsearch;
    AZExhibitorlistAdapter azExhibitorlistAdapter;
    public static ArrayList<AZExhibitorListPojo> mExhibitorList;
    RelativeLayout relativeLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.exhibitorlist_lay_az, null);


        editTextsearch = (SearchView) view.findViewById(R.id.editTextsearch);
        relativeLayout = view.findViewById(R.id.fragmentaz);
        editTextsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextsearch.setIconified(false);
            }
        });

        editTextsearch.setQueryHint("Search...");

        editTextsearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (azExhibitorlistAdapter != null)
                    azExhibitorlistAdapter.getFilter().filter(newText);
                return true;
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        hideKeyboard(getActivity());

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_exhibitor_az);
        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        mExhibitorList = new ArrayList<>();
        Log.e("Exhibitorlist",mExhibitorList.size()+"");

        getDatafromDB();

        return view;
    }


    private void getDatafromDB() {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity());
        databaseAccess.open();
        mExhibitorList = databaseAccess.getExhibitorlist();
        azExhibitorlistAdapter = new AZExhibitorlistAdapter(getActivity(), mExhibitorList);
        recyclerView.setAdapter(azExhibitorlistAdapter);
        azExhibitorlistAdapter.notifyDataSetChanged();
        databaseAccess.close();


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


}






