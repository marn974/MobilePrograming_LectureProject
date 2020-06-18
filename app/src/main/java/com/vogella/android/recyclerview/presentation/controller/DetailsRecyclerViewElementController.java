package com.vogella.android.recyclerview.presentation.controller;

import android.content.Intent;

import com.vogella.android.recyclerview.Singletons;
import com.vogella.android.recyclerview.presentation.model.Ghibli;
import com.vogella.android.recyclerview.presentation.view.DetailsRecyclerViewElement;
import com.vogella.android.recyclerview.presentation.view.MainActivity;

public class DetailsRecyclerViewElementController {
    DetailsRecyclerViewElement view;
    private static Ghibli movie;


    public DetailsRecyclerViewElementController(DetailsRecyclerViewElement view) {
        this.view = view;
    }

    public void onStart(){
        Intent intent = view.getIntent();
        String ghibliJson = intent.getStringExtra("movieKey");

        movie = Singletons.getGson().fromJson(ghibliJson, Ghibli.class);

        view.showDetails(movie);

    }

}
