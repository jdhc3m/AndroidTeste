package com.example.prigui.androidtest.ui.home

import android.content.Context
import com.example.prigui.androidtest.remote.model.FavoriteMoviesModel
import com.example.prigui.androidtest.remote.model.MovieResults

/**
 * Created by prigui on 10/08/2018.
 */


interface DetailMovieContract {

    interface View  {
        fun updateFavorite(isFavorite : Boolean)

    }

    interface Presenter {
        fun addFavoriteItems(context :Context, favoriteMoviesModel: FavoriteMoviesModel)
        fun attachView(mvpView: DetailMovieContract.View)
        fun detachView()
        fun removeFavoriteDetail(context :Context, movieId : Int)
        fun checkIsFavorite(context:Context, movieId: Int)
    }
}