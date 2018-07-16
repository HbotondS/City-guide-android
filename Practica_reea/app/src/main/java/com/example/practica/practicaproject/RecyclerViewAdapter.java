package com.example.practica.practicaproject;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static com.example.practica.practicaproject.R.drawable.*;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    public static final String INDEX_IMAGE = "com.example.practica.INDEX_IMAGE";
    public static final String MESSAGE = "value";

    private Context mContext;
    private List<Button> mData;

    public RecyclerViewAdapter(Context mContext, List<Button> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.cardview_item, viewGroup, false);

        return new MyViewHolder(view);
    }

    private void startMyActivity(String value, int img) {
        Intent intent = new Intent(mContext, ItemsActivity.class);
        intent.putExtra(INDEX_IMAGE, img);
        intent.putExtra(MESSAGE, value);
        mContext.startActivity(intent);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        holder.activity_title.setText(mData.get(i).getName());
        holder.activity_img.setImageResource(mData.get(i).getImg());

        holder.cardview.setOnClickListener((View v) -> {
            ViewGroup viewGroup = (ViewGroup) holder.cardview.getChildAt(0);
            TextView textView = (TextView) viewGroup.getChildAt(1);
            String text = textView.getText().toString();
            switch (text) {
                case "Bus" : {
                    startMyActivity("Bus", bus);
                    break;
                }
                case "Taxi" : {
                    startMyActivity("Taxi", taxi);
                    break;
                }
                case "Attractions" : {
                    startMyActivity("Attractions", camera);
                    break;
                }
                case "Museums" : {
                    startMyActivity("Museums", museum);
                    break;
                }
                case "Restaurants" : {
                    startMyActivity("Restaurants", restaruant);
                    break;
                }
                case "Hotels" : {
                    startMyActivity("Hotels", hotel);
                    break;
                }
                case "Shops" : {
                    startMyActivity("Shops", fashion_shop);
                    break;
                }
                case "Gyms" : {
                    startMyActivity("Gyms", gym);
                    break;
                }
                case "Movies" : {
                    startMyActivity("Movies", movie);
                    break;
                }
                default: {
                    Log.i("Button pressed", text);
                    Toast.makeText(mContext, "Clicked at button " + text, Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView activity_title;
        ImageView activity_img;
        CardView cardview;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            activity_title = itemView.findViewById(R.id.activity_name);
            activity_img = itemView.findViewById(R.id.activity_img);
            cardview = itemView.findViewById(R.id.cardview);
        }
    }
}
