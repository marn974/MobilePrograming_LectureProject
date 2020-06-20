package com.vogella.android.recyclerview.presentation.model;

import android.content.Context;

//Define return object
public class Ghibli {

    private String id;
    private String title;
    private String description;
    private String director;
    private String producer;
    private String release_date;
    private String[] people;
    private String rt_score;
    private String[] species;
    private String[] locations;
    private String url;

    public Ghibli(String id, String title, String description, String director, String producer, String release_date, String[] people, String rt_score, String[] species, String[] locations, String url) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.director = director;
        this.producer = producer;
        this.release_date = release_date;
        this.people = people;
        this.rt_score = rt_score;
        this.species = species;
        this.locations = locations;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDirector() {
        return director;
    }

    public String getProducer() {
        return producer;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public String[] getPeople() {
        return people;
    }

    public String getRt_score() {
        return rt_score;
    }

    public String[] getSpecies() {
        return species;
    }

    public String[] getLocations() {
        return locations;
    }

    public String getUrl() {
        return url;
    }

    public String getPosterName(String name){
        return "poster_"+name.replaceAll(" ", "_").toLowerCase();
    }

    public int getImagePosterId(Context context, String imageName){
        imageName = getPosterName(imageName);
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }
}
