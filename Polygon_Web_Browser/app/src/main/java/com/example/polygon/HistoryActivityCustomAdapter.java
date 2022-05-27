package com.example.polygon;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HistoryActivityCustomAdapter extends BaseAdapter {
    String[] titleStringH;
    String[] urlStringH;
    Bitmap[] iconsH;
    Context contextH;
    LayoutInflater inflaterH;

//    HistoryActivityCustomAdapter(Context contextH, String[] titleStringH, String[] urlStringH, Bitmap[] iconsH){
    HistoryActivityCustomAdapter(Context contextH, String[] titleStringH, String[] urlStringH){
        this.contextH = contextH;
        this.titleStringH =titleStringH;
        this.urlStringH =urlStringH;
//        this.iconsH = iconsH;
    }

    @Override
    public int getCount() {
        return urlStringH.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            inflaterH = (LayoutInflater) contextH.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflaterH.inflate(R.layout.history_sample_layout, parent, false);
        }

//        ImageView imageViewH = convertView.findViewById(R.id.iconIdBH);
        TextView titleH = convertView.findViewById(R.id.titleIdBH);
        TextView urlH = convertView.findViewById(R.id.urlIdBH);

//        imageViewH.setImageBitmap(iconsH[position]);
        titleH.setText(titleStringH[position]);
        urlH.setText(urlStringH[position]);

        return convertView;
    }
}

