package com.example.prigui.androidtest.utils;

import com.example.prigui.androidtest.BuildConfig;

/**
 * Created by prigui on 14/08/2018.
 */

public class Constants {

    public static final String PACKAGE_NAME = BuildConfig.APPLICATION_ID;

    public static final String API_KEY = "a9715d866256a5bc372291964fdbe2b2";
    public static final String  MOVIE_API_URL = "https://api.themoviedb.org/3/";
    public static final String  MOVIE_API_URL_AUTHENTICATION = "https://api.themoviedb.org/3/movie/76341/";
    public static final String  MOVIE_IMAGE_URL = "http://image.tmdb.org/t/p/w185";


    //EXTRA INTENT

    public static final String EXTRA_MOVIE = PACKAGE_NAME + "EXTRA_MOVIE";
    public static final String EXTRA_FAVORITE_MOVIE = PACKAGE_NAME + "EXTRA_FAVORITE_MOVIE";
    public static final String EXTRA_IS_FROM_HOME = PACKAGE_NAME + "EXTRA_IS_FROM_HOME";

    public static final int REQUEST_CODE_POST_ACTION = 100;



}
