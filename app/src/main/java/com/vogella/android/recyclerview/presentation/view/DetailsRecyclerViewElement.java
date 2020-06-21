package com.vogella.android.recyclerview.presentation.view;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.td1_afteras_update.R;

import com.vogella.android.recyclerview.presentation.controller.DetailsRecyclerViewElementController;
import com.vogella.android.recyclerview.presentation.model.Ghibli;


public class DetailsRecyclerViewElement extends AppCompatActivity {
    ImageView poster;
    TextView description, title;
    Toolbar bar;

    private DetailsRecyclerViewElementController controller;
    //Where i call my functions...
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_first);

        controller = new DetailsRecyclerViewElementController(DetailsRecyclerViewElement.this);

        poster = findViewById(R.id.imageView2);
        description =  findViewById(R.id.textViewDescription);
        title = findViewById(R.id.toolbar_title);
        bar = findViewById(R.id.toolbar);
        bar.setNavigationIcon(R.drawable.baseline_arrow_back_ios_black_18dp);
        setTitle("");
        setSupportActionBar(bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        controller.onStart();


        bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "Back to main page", Toast.LENGTH_LONG).show();
                controller.navigationIconClicked();
            }
        });

        }


    public void showDetails(Ghibli movie) {
        description.setText(movie.getDescription());
        title.setText(movie.getTitle());

        String ressource = movie.getPosterName(movie.getTitle());
        poster.setImageResource(movie.getImageId(getApplicationContext(), ressource));

    }







}

