package com.vogella.android.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.td1_afteras_update.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

