package com.vogella.android.recyclerview.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.td1_afteras_update.R;
import com.vogella.android.recyclerview.presentation.model.Ghibli;

public class DetailsRecyclerViewElement extends AppCompatActivity {
    ImageView poster;
    TextView description, title;
    Toolbar bar;
    Ghibli movie;
    //Where i call my functions...
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_first);

        poster = (ImageView) findViewById(R.id.imageView2);
        description = (TextView) findViewById(R.id.textViewDescription);
        title = (TextView) findViewById(R.id.textViewMovieTitle);

        Intent intent = getIntent();
        String tempTitle = intent.getStringExtra("title");
        String tempDescription = intent.getStringExtra("description");
        //System.out.println(tempDescription + " " +tempTitle);

        showDescription(tempDescription);
        showTitle(tempTitle);

    }

    private void showDescription(String tempDescription){
        description.setText(tempDescription);
    }

    private void showTitle(String tempTitle){
        title.setText(tempTitle);
    }


}

