package com.example.prigui.androidtest.remote.model;

import java.io.Serializable;

/**
 * Created by prigui on 16/08/2018.
 */

public class FavoriteMoviesModel implements Serializable {

    private int movieId;
    private String name;
    private String date;
    private String poster;
    private String overview;

    public FavoriteMoviesModel(){

    }

    public FavoriteMoviesModel(int id, String name, String date, String poster,
                String overview){
        this.movieId = id;
        this.name = name;
        this.date = date;
        this.poster = poster;
        this.overview = overview;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getPoster() {
        return poster;
    }

    public String getOverview() {
        return overview;
    }


    public void setMovieId(int movie_id) {
        this.movieId = movie_id;
    }

    public void setName(String movie_name) {
        this.name = movie_name;
    }

    public void setDate(String movie_date) {
        this.date = movie_date;
    }

    public void setPoster(String movie_poster) {
        this.poster = movie_poster;
    }

    public void setOverview(String movie_overview) {
        this.overview = movie_overview;
    }




}
