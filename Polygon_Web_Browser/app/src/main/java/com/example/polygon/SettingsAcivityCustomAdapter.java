package com.example.polygon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.zip.Inflater;

public class SettingsAcivityCustomAdapter extends BaseAdapter {

    String[] stringList;
    int[] icons;
    Context context;
    LayoutInflater inflater;

    SettingsAcivityCustomAdapter(Context context,String[] stringList,int[] icons){
        this.context = context;
        this.stringList =stringList;
        this.icons = icons;
    }

    @Override
    public int getCount() {
        return stringList.length;
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
            convertView = inflater.inflate(R.layout.settings_sample_layout, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.iconId);
        TextView textView = convertView.findViewById(R.id.textViewId);

        imageView.setImageResource(icons[position]);
        textView.setText(stringList[position]);

        return convertView;
    }
}
