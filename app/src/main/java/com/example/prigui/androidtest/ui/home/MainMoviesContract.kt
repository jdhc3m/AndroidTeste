package com.example.prigui.androidtest.ui.home

import android.content.Context
import com.example.prigui.androidtest.remote.model.FavoriteMoviesModel
import com.example.prigui.androidtest.remote.model.MovieResults

/**
 * Created by prigui on 10/08/2018.
 */


interface MainMoviesContract {

    interface View  {
        fun displayItems(movies : List<MovieResults>)
        fun displayFavorites(favorites : List<FavoriteMoviesModel>)
        fun displayFavoritesDetail(favorites : FavoriteMoviesModel)
        fun displayLoading(loading : Boolean)
        fun displayError(message: String)
    }

    interface Presenter {
        fun attachView(mvpView: View)
        fun detachView()
        fun loadItems(context : Context, page : Int?)
        fun loadFavoriteItems(context :Context)
        fun loadFavoriteDetail(context :Context, movieId : Int)
    }
}