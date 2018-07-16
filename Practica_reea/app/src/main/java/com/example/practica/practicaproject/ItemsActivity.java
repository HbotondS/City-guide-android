package com.example.practica.practicaproject;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import static com.example.practica.practicaproject.R.array.*;

public class ItemsActivity extends AppCompatActivity {

    private ImageView indexImg;

    private String[] name = new String[]{""};
    private String[] description  = new String[]{""};

    private void setAttr(int name, int desc) {
        this.name = getResources().getStringArray(name);
        this.description = getResources().getStringArray(desc);
    }

    private void setAttr(int name) {
        this.name = getResources().getStringArray(name);
        this.description = new String[this.name.length];
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        ListView items = findViewById(R.id.items);

        indexImg = this.findViewById(R.id.indexImg);

        Intent intent = getIntent();
        int index_image = intent.getExtras().getInt(RecyclerViewAdapter.INDEX_IMAGE);
        String value = intent.getStringExtra(RecyclerViewAdapter.MESSAGE);

        switch (value) {
            case "Bus" : {
                setAttr(bus_number, bus_start_end);
                break;
            }
            case "Taxi" : {
                setAttr(taxi_name);
                break;
            }
            case "Attractions" : {
                setAttr(attraction_name);
                break;
            }
            case "Museums" : {
                setAttr(museum_name);
                break;
            }
            case "Restaurants" : {
                setAttr(restaurant_name);
                break;
            }
            case "Hotels" : {
                setAttr(hotel_name);
                break;
            }
            case "Shops" : {
                setAttr(shop_name);
                break;
            }
            case "Gyms" : {
                setAttr(gym_name);
                break;
            }
            case "Movies" : {
                setAttr(movie_name);
                break;
            }
        }

        BtnAdapter bba = new BtnAdapter(this, index_image, name, description);
        items.setAdapter(bba);
    }
}
