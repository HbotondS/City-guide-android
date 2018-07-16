package com.example.practica.practicaproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BtnAdapter extends BaseAdapter{
    LayoutInflater mInfalter;
    String[] name;
    String[] description;
    int index_image;

    public BtnAdapter(Context c, int index_image, String[] name, String[] description) {
        this.mInfalter = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.index_image = index_image;
        this.name = name;
        this.description = description;
    }

    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int i) {
        return name[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = mInfalter.inflate(R.layout.my_items_layout, null);
        ImageView indexImg = v.findViewById(R.id.indexImg);
        TextView indexNumber = v.findViewById(R.id.item_indexNumber);
        TextView name = v.findViewById(R.id.item_name);

        String number = this.name[i];
        String route = description[i];

        indexImg.setImageResource(index_image);
        indexNumber.setText(number);
        name.setText(route);

        return v;
    }
}
