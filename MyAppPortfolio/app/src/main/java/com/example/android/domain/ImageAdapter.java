package com.example.android.domain;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.android.myappportfolio.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Kunal on 5/30/2016.
 */
public class ImageAdapter extends ArrayAdapter<PopularMovie>{

    private Context mContext;
    private int layoutResourceId;
    private static List<PopularMovie> gridData = new ArrayList<PopularMovie>();
    private static String[] imageUrls;
    private final String LOG_TAG = ImageAdapter.class.getSimpleName();

    public ImageAdapter(Context context, int resource, List<PopularMovie> gridData) {
        super(context, resource, gridData);
        this.mContext = context;
        this.layoutResourceId = resource;
        this.gridData = gridData;

    }

    public void setGridData(List<String> gridData,List<PopularMovie> moviesDetailList){
        this.gridData = moviesDetailList;
        this.imageUrls = gridData.toArray(new String[gridData.size()]);
        this.notifyDataSetChanged();
    }


    public String[] getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(String[] imageUrls) {
        this.imageUrls = imageUrls;
    }

    @Override
    public int getCount() {
        return gridData.size();
    }

    @Override
    public PopularMovie getItem(int position) {
        Log.v(LOG_TAG, " gridData SIZE : " + this.gridData.size());
        PopularMovie mv = this.gridData.get(position);
        return mv;
    }

/*    @Override
    public int getPosition(String item) {
        return super.getPosition(item);
    }*/



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        View vw = convertView;
        ViewHolder holder = new ViewHolder();;


        if(vw==null){
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            vw=inflater.inflate(layoutResourceId,parent,false);
            holder.imageView =  (ImageView) vw.findViewById(R.id.grid_item_layout);
            vw.setTag(holder);
        }else {
            Log.v(LOG_TAG, " POSITION : " + position);
            Log.v(LOG_TAG, " mContext : " + mContext);
            Log.v(LOG_TAG, " convertView : " + convertView);
            Log.v(LOG_TAG, " URL : " + imageUrls[position]);

            holder = (ViewHolder) vw.getTag();
            Log.v(LOG_TAG, " ImageView : " + holder.imageView);
        }
        Log.v(LOG_TAG, " POSITION : " + position);
        Log.v(LOG_TAG, " mContext : " + mContext);
        Log.v(LOG_TAG, " convertView : " + convertView);
        Log.v(LOG_TAG, " URL : " + imageUrls[position]);
        Log.v(LOG_TAG, " ImageView : " + holder.imageView);

        Picasso.with(mContext)
                    .load(gridData.get(position).getPosterUrl()).into(holder.imageView);

        return vw;
    }

    static class ViewHolder {
        ImageView imageView;
    }


}
