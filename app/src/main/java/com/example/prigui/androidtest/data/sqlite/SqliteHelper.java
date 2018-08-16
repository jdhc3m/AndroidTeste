package com.example.prigui.androidtest.data.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.prigui.androidtest.remote.model.FavoriteMoviesModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by benidict on 02/09/2017.
 */

@SuppressWarnings("WeakerAccess")
public class SqliteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "sample_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = SqliteHelper.class.getSimpleName();

    public SqliteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SqliteTable.DB_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SqliteTable.DB_MOVIE);
        onCreate(db);
    }

    public boolean insertData(String table, ContentValues values){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.insert(table, null, values);
        if (result == -1){
            Log.d(TAG, "failed to save data!");
            return false;
        }else{
            Log.d(TAG, "save data successful");
            return true;
        }
    }
    

    public FavoriteMoviesModel getMovieDetail(int movieId){
        FavoriteMoviesModel movie = new FavoriteMoviesModel();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns={
                SqliteTable.COL_MOVIE_ID,
                SqliteTable.COL_MOVIE_NAME,
                SqliteTable.COL_MOVIE_DATE,
                SqliteTable.COL_MOVIE_POSTER,
                SqliteTable.COL_MOVIE_OVERVIEW
        };

        String selection = SqliteTable.COL_MOVIE_ID + " =? " ;
        String[] args={String.valueOf(movieId)};

        Cursor cursor = db.query(SqliteTable.TABLE_MOVIE, columns,
                selection, args, null, null, null);

        if (cursor != null && cursor.moveToFirst()){

            movie = new FavoriteMoviesModel();
            movie.setMovieId(cursor.getInt(0)) ;
            movie.setName(cursor.getString(1));
            movie.setDate(cursor.getString(2));
            movie.setPoster(cursor.getString(3));
            movie.setOverview(cursor.getString(4));

            return movie;
        }

        return null;
    }

    public List<FavoriteMoviesModel> getFavoriteMovies(){
        List<FavoriteMoviesModel> movies = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns={
                SqliteTable.COL_MOVIE_ID,
                SqliteTable.COL_MOVIE_NAME,
                SqliteTable.COL_MOVIE_DATE,
                SqliteTable.COL_MOVIE_POSTER,
                SqliteTable.COL_MOVIE_OVERVIEW
        };

        Cursor cursor = db.query(SqliteTable.TABLE_MOVIE, columns,
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                FavoriteMoviesModel movie = new FavoriteMoviesModel();
                movie.setMovieId(cursor.getInt(0));
                movie.setName(cursor.getString(1));
                movie.setDate(cursor.getString(2));
                movie.setPoster(cursor.getString(3));
                movie.setOverview(cursor.getString(4));
                movies.add(movie);


            } while (cursor.moveToNext());
        }
        db.close();

        return movies;

    }


    /**
     * This method delete movie details permanently, and it returns boolean value.
     * @param movieId param email provide email of the movie that are logged in the app.
     * @return true/false.
    **/
    public boolean deleteMovie(int movieId){
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.delete(SqliteTable.TABLE_MOVIE, SqliteTable.COL_MOVIE_ID + " =? ",
                new String[]{String.valueOf(movieId)}) > 0;
    }

}
