package com.example.prigui.androidtest.data.sqlite;

/**
 * Created by benidict on 02/09/2017.
 */

@SuppressWarnings("WeakerAccess")
public class SqliteTable {

   /**
    * This class provide tables
    **/

    public static final String TABLE_MOVIE = "table_movie";
    public static final String COL_MOVIE_ID = " col_movie_id";
    public static final String COL_MOVIE_NAME = " col_movie_name";
    public static final String COL_MOVIE_DATE = " col_movie_date";
    public static final String COL_MOVIE_POSTER = " col_movie_poster";
    public static final String COL_MOVIE_OVERVIEW = " col_movie_overview";


    public static final String DB_MOVIE = "CREATE TABLE " + TABLE_MOVIE + "(" + COL_MOVIE_ID + " INTEGER PRIMARY KEY, "
            + COL_MOVIE_NAME + " TEXT, " + COL_MOVIE_DATE + " TEXT, " + COL_MOVIE_POSTER + " TEXT, "
            + COL_MOVIE_OVERVIEW + " TEXT " + ")";


}
