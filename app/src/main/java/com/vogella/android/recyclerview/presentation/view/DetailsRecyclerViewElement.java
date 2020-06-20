package com.vogella.android.recyclerview.presentation.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.td1_afteras_update.R;
import com.vogella.android.recyclerview.Singletons;
import com.vogella.android.recyclerview.presentation.controller.DetailsRecyclerViewElementController;
import com.vogella.android.recyclerview.presentation.model.Ghibli;

public class DetailsRecyclerViewElement extends AppCompatActivity {
    ImageView poster;
    TextView description, title;
    Toolbar bar;
    Ghibli movie;
    private DetailsRecyclerViewElementController controller;
    //Where i call my functions...
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_first);

        controller = new DetailsRecyclerViewElementController(DetailsRecyclerViewElement.this);

        poster = findViewById(R.id.imageView2);
        description =  findViewById(R.id.textViewDescription);
        title = findViewById(R.id.textViewMovieTitle);

        controller.onStart();



    }

    public void showDetails(Ghibli movie) {
        description.setText(movie.getDescription());
        title.setText(movie.getTitle());

        String ressource = movie.getPosterName(movie.getTitle());
        poster.setImageResource(movie.getImageId(getApplicationContext(), ressource));

    }



}

