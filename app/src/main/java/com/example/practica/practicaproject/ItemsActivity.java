package com.example.practica.practicaproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static com.example.practica.practicaproject.R.array.*;

public class ItemsActivity extends AppCompatActivity {

    private String[] name = new String[]{""};
    private String[] description  = new String[]{""};

    private void setAttr(int name, int desc) {
        this.name = getResources().getStringArray(name);
        this.description = getResources().getStringArray(desc);
    }

    private void setAttr(int name) {
        this.description = getResources().getStringArray(name);
        this.name = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        ListView items = findViewById(R.id.items);

        Intent intent = getIntent();
        int index_image = intent.getExtras().getInt(RecyclerViewAdapter.INDEX_IMAGE);
        String value = intent.getStringExtra(RecyclerViewAdapter.MESSAGE);

        switch (value) {
            case "Bus" : {
                BusHolder busHolder = new BusHolder(getResources().getStringArray(bus_number),
                        getResources().getStringArray(bus_start_end),
                        getResources().getStringArray(bus_description));

                name = busHolder.getBusNumbers();
                description = busHolder.getBusStartEnds();
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
            case "Movies&Theatres" : {
                setAttr(movie_and_theatre_name);
                break;
            }
            case "Clubs" : {
                setAttr(club_name);
                break;
            }
        }

        BtnAdapter bba = new BtnAdapter(this, index_image, name, description);
        items.setAdapter(bba);

        items.setOnItemClickListener((parent, view, position, id) -> {
//            TextView textView = view.findViewById(R.id.item_indexNumber);
//            Log.d("TAG", textView.getText().toString());
            Log.d("TAG", name[position]);
            Log.d("TAG", "Position: " + position);
            Log.d("TAG", "id: " + id);
        });
    }
}
