package com.example.prigui.androidtest.remote.network

import com.example.prigui.androidtest.remote.model.MoviesModel
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query



/**
 * Created by prigui on 10/08/2018.
 */


interface MoviesDataSource {

    @GET("movie/popular")
    fun getMovies(@Query("api_key") apiKey: String?,
                  @Query("page") page: Int?): Observable<Response<MoviesModel>>

}

