package com.ramanora.stona.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramanora.stona.R;
import com.ramanora.stona.activities.ActivityNewsAndEventMainPage;
import com.ramanora.stona.bean.NewsArticlesPojo;
import com.ramanora.stona.fragments.FragmentNewsInDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.DataHolder> {

    private Context mContext;
    private ArrayList<NewsArticlesPojo> mArrayListArticles;





    public NewsAdapter(Context mContext, ArrayList<NewsArticlesPojo> mArrayListCountries) {
        this.mContext = mContext;
        this. mArrayListArticles = mArrayListCountries;
    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mContext);
        View view=inflater.inflate(R.layout.news_lay_news_adapter,parent,false);
        DataHolder dataHolder=new DataHolder(view);
        return dataHolder;
    }

    @Override
    public void onBindViewHolder(final DataHolder holder, final int position) {

        final NewsArticlesPojo articlesPojo= mArrayListArticles.get(position);


        holder.mTvTitle.setText(articlesPojo.getmTitle());
        holder.mTvSummary.setText(articlesPojo.getmSummary());
        holder.mTvPublishAt.setText(articlesPojo.getmPublishAt());
        holder.mTvCompanyName.setText(articlesPojo.getmCompanyName());



        Picasso.with(mContext).
                load(articlesPojo.getmImageOfCompany()).
                into(holder.mImgOfCompany);





        Picasso.with(mContext).
                load(articlesPojo.getmNEwsimage()).

                into(holder.mImgOfNews);



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentNewsInDetails newsInDetailsFragment=new FragmentNewsInDetails();
                Bundle bundle=new Bundle();
                bundle.putSerializable("Data",articlesPojo);
                newsInDetailsFragment.setArguments(bundle);
                ((ActivityNewsAndEventMainPage)mContext).getSupportFragmentManager().beginTransaction().replace(R.id.actNewsAndUpdadeId, newsInDetailsFragment,"").addToBackStack(null).commit();

            }
        });




    }

    @Override
    public int getItemCount() {
        return mArrayListArticles.size();
    }

    public class DataHolder extends RecyclerView.ViewHolder {

        TextView mTvTitle,mTvSummary,mTvwebUrl,mTvPublishAt,mTvCompanyName;
       ImageView mImgOfCompany, mImgOfNews ;
      //  ImageButton mShare;
        ImageView mLikeBtn;
        TextView txtLikes;

        public DataHolder(View itemView) {
            super(itemView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            mTvTitle= (TextView) itemView.findViewById(R.id.TvTitle);
            mTvSummary=itemView.findViewById(R.id.TvSummary);
            mTvPublishAt=itemView.findViewById(R.id.TvPublishAt);
            mTvPublishAt=itemView.findViewById(R.id.TvPublishAt);
            mTvCompanyName=itemView.findViewById(R.id.TvCompanyName);
            mImgOfCompany=itemView.findViewById(R.id.imgCompany);
            mImgOfNews=itemView.findViewById(R.id.imgNews);
          //  mShare=itemView.findViewById(R.id.shareNews);
           // mLikeBtn=itemView.findViewById(R.id.imgBtnlikes);
           // txtLikes=itemView.findViewById(R.id.txt);

/*

            mLikeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int id = (int) mLikeBtn.getTag();
                    if (id==R.drawable.ic_thumb_up_black_24dp)
                    {
                        mLikeBtn.setTag(R.drawable.ic_thumb_up_blue_24dp);
                        mLikeBtn.setImageResource(R.drawable.ic_thumb_up_blue_24dp);
                    }
                    else {

                        mLikeBtn.setTag(R.drawable.ic_thumb_up_black_24dp);
                        mLikeBtn.setImageResource(R.drawable.ic_thumb_up_black_24dp);

                    }

                }
            });
*/






        }
    }
}
