package com.example.practica.practicaproject;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
        if (name == null) {
            return description.length;
        }
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

        if (this.name == null) {
            indexNumber.setText("");
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) name.getLayoutParams();

            int sizeInDP = 70;
            int marginInDP = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, sizeInDP, v.getResources().getDisplayMetrics());

            lp.setMarginStart(marginInDP);
            name.setLayoutParams(lp);
        } else {
            String number = this.name[i];
            indexNumber.setText(number);
        }

        indexImg.setImageResource(index_image);

        String route = description[i];
        name.setText(route);

        return v;
    }
}
