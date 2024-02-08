package com.ramanora.stona.adapter;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.ramanora.stona.R;
import com.ramanora.stona.bean.CameraPojo;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class VisitingImageAdapter extends ArrayAdapter<CameraPojo> {
	Context context;
	    int layoutResourceId;

	    ArrayList<CameraPojo> data=new ArrayList<CameraPojo>();

	    public VisitingImageAdapter(Context context, int layoutResourceId, ArrayList<CameraPojo> data) {
	        super(context, layoutResourceId, data);
	        this.layoutResourceId = layoutResourceId;
	        this.context = context;
	        this.data = data;
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        View view = convertView;
	        ImageHolder holder = null;
	       
	        if(view == null)
	        {
	            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
	            view = inflater.inflate(layoutResourceId, parent, false);
				holder = new ImageHolder();
	            holder.imgIcon = (ImageView)view.findViewById(R.id.imgIcon);
	            view.setTag(holder);
	        }
	        else
	        {
	            holder = (ImageHolder)view.getTag();
	        }
	       
	        CameraPojo cameraPojo = data.get(position);

	        //convert byte to bitmap take from contact class
	        
	        byte[] outImage=cameraPojo._image;
	        ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
	        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
	        holder.imgIcon.setImageBitmap(theImage);
	       return view;
	       
	    }
	   
	    static class ImageHolder
	    {
	        ImageView imgIcon;
	    }



	}
