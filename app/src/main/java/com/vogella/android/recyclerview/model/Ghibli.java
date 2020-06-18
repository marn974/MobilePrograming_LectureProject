package com.vogella.android.recyclerview.model;

//Define return object
public class Ghibli {

    private String id;
    private String title;
    private String description;
    private String director;
    private String producer;
    private String releaseDate;
    private String[] people;
    private int rtScore;
    private String[] species;
    private String[] locations;
    private String url;

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
        return releaseDate;
    }

    public String[] getPeople() {
        return people;
    }

    public int getRtScore() {
        return rtScore;
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
}
