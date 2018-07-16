package com.example.practica.practicaproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout mainGrid = findViewById(R.id.mainGrid);

        setSingleEvent(mainGrid);
    }

    private void setSingleEvent(GridLayout mainGrid) {
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            int finalI = i;
            cardView.setOnClickListener((View v) -> {
                if (finalI == 0) {
                    //Toast.makeText(MainActivity.this, "Not available", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, Recyclerview.class);
                    startActivity(intent);
                } else {
                    ViewGroup viewGroup = (ViewGroup) cardView.getChildAt(0);
                    TextView textView = (TextView) viewGroup.getChildAt(1);
                    String text = textView.getText().toString();
                    Log.i("Button pressed", text);
                    Toast.makeText(MainActivity.this, "Clicked at button " + text, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
