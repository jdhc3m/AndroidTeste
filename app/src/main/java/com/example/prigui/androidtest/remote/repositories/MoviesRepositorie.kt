package com.example.prigui.androidtest.remote.repositories

import com.example.prigui.androidtest.remote.model.MovieDetailModel
import com.example.prigui.androidtest.remote.model.MoviesModel
import rx.Single

/**
 * Created by prigui on 10/08/2018.
 */

object MoviesRepository {

    private val repository = MoviesRemoteDataSource

    fun getMovies(page : Int?): Single<List<MoviesModel>> {
        return repository.getMovies(page)
    }

    fun getMovieDetail(movieId: String): Single<MovieDetailModel> {
        return repository.getMovieDetail(movieId)
    }

}