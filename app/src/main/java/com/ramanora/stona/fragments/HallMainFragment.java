package com.ramanora.stona.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.ramanora.stona.R;
import com.ramanora.stona.adapter.HallMainAdapter;
import com.ramanora.stona.bean.AZExhibitorListPojo;
import com.ramanora.stona.database.DatabaseAccess;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HallMainFragment extends Fragment {


    RecyclerView recyclerView;
    RelativeLayout relativeLayout;
    LinearLayoutManager manager;
    HallMainAdapter hallExhbitorlistAdapter;
    ArrayList<AZExhibitorListPojo> mExhibitorList;
    ArrayList<AZExhibitorListPojo> mExhibitorList1;
    SearchView editTextsearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.hall_exhibitorlist_lay_hall_exhibitor, null);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_exhibitor_hall);
        relativeLayout = view.findViewById(R.id.hallfragment);
        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        editTextsearch = (SearchView) view.findViewById(R.id.editTextsearch);

        editTextsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextsearch.setIconified(false);
            }
        });

        editTextsearch.setQueryHint("Search...");


        getDatafromDB();

        editTextsearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (hallExhbitorlistAdapter != null)
                    hallExhbitorlistAdapter.getFilter().filter(newText);
                return true;
            }
        });

        return view;
    }

    private void getDatafromDB() {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity());
        databaseAccess.open();
        mExhibitorList = databaseAccess.getHalllist();
       // mExhibitorList1 = databaseAccess.getExhibitorlist();
        hallExhbitorlistAdapter = new HallMainAdapter(getActivity(), mExhibitorList);
        recyclerView.setAdapter(hallExhbitorlistAdapter);
        hallExhbitorlistAdapter.notifyDataSetChanged();
        databaseAccess.close();

    }

}
