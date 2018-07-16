package com.example.practica.practicaproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


/** MAIN*/
public class Recyclerview extends AppCompatActivity {

    RecyclerView myrv;
    List<Button> lstButton;
    private int numberOfColumns = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        initButtons();

        initRecycleView();
    }

    private void initRecycleView() {
        myrv = findViewById(R.id.recyclerView);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, lstButton);
        myrv.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        myrv.setAdapter(recyclerViewAdapter);
    }

    private void initButtons() {
        lstButton = new ArrayList<>();
        lstButton.add(new Button("Bus", R.drawable.bus));
        lstButton.add(new Button("Taxi", R.drawable.taxi));
        lstButton.add(new Button("Attractions", R.drawable.camera));
        lstButton.add(new Button("Museums", R.drawable.museum));
        lstButton.add(new Button("Restaurants", R.drawable.restaruant));
        lstButton.add(new Button("Hotels", R.drawable.hotel));
        lstButton.add(new Button("Shops", R.drawable.fashion_shop));
        lstButton.add(new Button("Gyms", R.drawable.gym));
        lstButton.add(new Button("Movies", R.drawable.movie));
        lstButton.add(new Button("Theatres", R.drawable.theatre));
        lstButton.add(new Button("Clubs", R.drawable.party));
        lstButton.add(new Button("Pubs", R.drawable.pub));
    }
}
