package com.ramanora.stona.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ramanora.stona.R;
import com.ramanora.stona.adapter.NewsAdapter;
import com.ramanora.stona.bean.NewsArticlesPojo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentNewsFeed extends Fragment {

    private RequestQueue mRequestQueue;
    RecyclerView recyclerView;
    LinearLayoutManager manager;
    NewsAdapter mNewsAdapter;
    FrameLayout frameLayout;
    private static ArrayList<NewsArticlesPojo> mArrayNews;
   // ProgressDialog progressDialog;
    ProgressBar mProgressBar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_lay_news_feed_recy, null);


        String url = "http://stona.exhibitionz.in/Adminportal/jsonRev.php";

        mRequestQueue = Volley.newRequestQueue(getActivity());
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_id);
        frameLayout = view.findViewById(R.id.fragmentnewsfeed);
        mProgressBar = view.findViewById(R.id.progressbar);

        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        mArrayNews = new ArrayList<>();
        mNewsAdapter = new NewsAdapter(getContext(), mArrayNews);
        recyclerView.setAdapter(mNewsAdapter);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("News");

                    System.out.println("get" + response.toString());
//save count in sp
                    //if no count present in sp then just insert count in sp
                    //on 2nd time calculate old count-new count and add it in sp newcount,  count=jsonArray.length()


                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                        String titile = jsonObject1.getString("title");
                        String summary = jsonObject1.getString("summary");
                        String description = jsonObject1.getString("description");
                        // String webUrl = jsonObject1.getString("tweburl");
                        String newsImage = jsonObject1.getString("image");
                        String publishAt = jsonObject1.getString("publishAt");
                        String companyName = jsonObject1.getString("CompanyName");
                        String imageOfcompany = jsonObject1.getString("ImageOfCompany");


                        // String like=articlesPojo.getLikes();

                        NewsArticlesPojo articlesPojo = new NewsArticlesPojo(titile, summary, description, newsImage, publishAt, companyName, imageOfcompany);
                        mArrayNews.add(articlesPojo);
                        mNewsAdapter.notifyDataSetChanged();
                       // progressDialog.dismiss();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

               /* Toast.makeText(getActivity(), "No Interner Connection, please check your internet connection ", Toast.LENGTH_SHORT).show();
                error.getMessage();*/
                Snackbar snackbar = Snackbar
                        .make(frameLayout, "No internet connection!", Snackbar.LENGTH_LONG)
                        .setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });

                snackbar.setDuration(5000);
                snackbar.show();
                //progressDialog.dismiss();

            }
        });


        mRequestQueue.add(jsonObjectRequest);
      /*  progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait, while fetching the data...");
        progressDialog.show();*/


        return view;
    }
}






