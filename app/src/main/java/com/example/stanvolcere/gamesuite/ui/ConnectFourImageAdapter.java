package com.example.stanvolcere.gamesuite.ui;


import android.widget.BaseAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import android.widget.GridView;
import android.widget.ImageView;

import com.example.stanvolcere.gamesuite.R;


public class ConnectFourImageAdapter extends BaseAdapter {

    private Context mContext;
    public ConnectFourImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        //return mThumbIds.length;
        return 42;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(100, 100));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setPadding(0, 3, 0, 0);

            imageView.setImageResource(mThumbIds[0]);

        } else {
            imageView = (ImageView) convertView;
        }

        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.white,
            R.drawable.yellow,
            R.drawable.red
    };

}

