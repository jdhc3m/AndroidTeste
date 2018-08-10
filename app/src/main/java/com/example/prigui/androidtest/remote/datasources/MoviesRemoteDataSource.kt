package com.example.prigui.androidtest.remote.datasources

import com.example.prigui.androidtest.remote.model.MovieDetailModel
import com.example.prigui.androidtest.remote.model.MoviesModel
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Single

/**
 * Created by prigui on 10/08/2018.
 */

class CharactersRemoteDataSource : MoviesDataSource {
    override fun getMovie(movieId: String?): Single<List<MovieDetailModel>> {
        this.api.getMovie(movieId)
    }

    override fun getMovieDetail(movieId: String?): Single<List<MoviesModel>> {

    }

    private val api: MoviesDataSource
    private val URL = "https://sky-exercise.herokuapp.com/api/"

    val characters: rx.Observable<List<SkyResponseModel>>
        get() = this.api.getMovie()

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

        this.api = retrofit.create<CharactersDataSource>(CharactersDataSource::class.java!!)
    }
}


