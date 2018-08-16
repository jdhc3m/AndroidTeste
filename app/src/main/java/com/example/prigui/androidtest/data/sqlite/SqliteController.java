package com.example.prigui.androidtest.data.sqlite;

import android.content.ContentValues;
import android.content.Context;

import com.example.prigui.androidtest.remote.model.FavoriteMoviesModel;

import java.util.List;

/**
 * Created by benidict on 02/09/2017.
 */

@SuppressWarnings("WeakerAccess")
public class SqliteController {

    private SqliteHelper sqliteHelper;
    Context context;

    public SqliteController(Context mContext){
        this.context = mContext;
        sqliteHelper = new SqliteHelper(context);
    }

    public boolean saveUserData(FavoriteMoviesModel movie){
        ContentValues values = new ContentValues();
        values.put(SqliteTable.COL_MOVIE_ID, movie.getMovieId());
        values.put(SqliteTable.COL_MOVIE_NAME, movie.getName());
        values.put(SqliteTable.COL_MOVIE_DATE, movie.getDate());
        values.put(SqliteTable.COL_MOVIE_POSTER, movie.getPoster());
        values.put(SqliteTable.COL_MOVIE_OVERVIEW, movie.getOverview());
        return sqliteHelper.insertData(SqliteTable.TABLE_MOVIE, values);
    }



    public FavoriteMoviesModel getMovieDetails(int movieId){
        return sqliteHelper.getMovieDetail(movieId);
    }

    public List<FavoriteMoviesModel> getFavoriteMovies(){
        return sqliteHelper.getFavoriteMovies();
    }

    public boolean deleteAccount(int movieId){
        return sqliteHelper.deleteMovie(movieId);
    }

}
