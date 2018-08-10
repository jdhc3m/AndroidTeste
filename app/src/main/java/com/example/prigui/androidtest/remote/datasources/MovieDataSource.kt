package com.example.prigui.androidtest.remote.datasources

import com.example.prigui.androidtest.remote.model.MovieDetailModel
import com.example.prigui.androidtest.remote.model.MoviesModel
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Single

/**
 * Created by prigui on 10/08/2018.
 */


interface MoviesDataSource {

    @GET("getDetail")
    fun getMovie(@Query("text") movieId :String?) : Single<List<MovieDetailModel>>

    @GET("getMovies")
    fun getMovieDetail(@Query("text") movieId :String?) : Single<List<MoviesModel>>

}

