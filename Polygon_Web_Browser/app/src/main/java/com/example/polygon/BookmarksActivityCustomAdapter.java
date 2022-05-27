package com.example.polygon;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BookmarksActivityCustomAdapter extends BaseAdapter {
    String[] titleString;
    String[] urlString;
    Bitmap[] icons;
    Context context;
    LayoutInflater inflater;

    BookmarksActivityCustomAdapter(Context context, String[] titleString, String[] urlString, Bitmap[] icons){
        this.context = context;
        this.titleString =titleString;
        this.urlString =urlString;
        this.icons = icons;
    }

    @Override
    public int getCount() {
        return urlString.length;
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
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.bookmarks_sample_layout, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.iconIdB);
        TextView title = convertView.findViewById(R.id.titleIdB);
        TextView url = convertView.findViewById(R.id.urlIdB);

        imageView.setImageBitmap(icons[position]);
        title.setText(titleString[position]);
        url.setText(urlString[position]);

        return convertView;
    }
}
