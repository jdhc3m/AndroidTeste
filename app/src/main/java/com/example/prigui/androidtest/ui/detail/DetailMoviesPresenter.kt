package com.example.prigui.androidtest.ui.home

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.example.prigui.androidtest.data.sqlite.SqliteController
import com.example.prigui.androidtest.data.sqlite.SqliteHelper
import com.example.prigui.androidtest.remote.model.FavoriteMoviesModel
import com.example.prigui.androidtest.remote.model.MovieResults
import com.example.prigui.androidtest.remote.model.MoviesModel
import com.example.prigui.androidtest.remote.network.MovieApi
import com.example.prigui.androidtest.remote.network.NetworkConnection
import com.example.prigui.androidtest.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


/**
 * Created by prigui on 10/08/2018.
 */


class DetailMoviesPresenter : AppCompatActivity(), DetailMovieContract.Presenter {

    private var mView: DetailMovieContract.View? = null

    internal lateinit var sqliteController: SqliteController



    override fun addFavoriteItems(context: Context, favoriteMoviesModel: FavoriteMoviesModel) {
        sqliteController = SqliteController(context)
        mView?.updateFavorite(true)
    }

    override fun removeFavoriteDetail(context: Context, movieId : Int) {
        sqliteController = SqliteController(context)
        sqliteController.deleteAccount(movieId)
        mView?.updateFavorite(false)
    }

    override fun checkIsFavorite(context: Context, movieId: Int) {
        sqliteController = SqliteController(context)
        var response = sqliteController.getMovieDetails(movieId)
        if (response != null){
            mView?.updateFavorite(true)
        } else {
            mView?.updateFavorite(false)

        }
    }

    override fun attachView(mvpView: DetailMovieContract.View) {
        mView = mvpView
    }

    override fun detachView() {
        mView = null
    }


}