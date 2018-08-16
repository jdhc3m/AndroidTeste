package com.example.prigui.androidtest.ui.home

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.example.prigui.androidtest.data.sqlite.SqliteController
import com.example.prigui.androidtest.data.sqlite.SqliteHelper
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


class MainMoviesPresenter: AppCompatActivity(), MainMoviesContract.Presenter {

    private var mView: MainMoviesContract.View? = null
    private lateinit var mContext : Context
    private var mRepository = MovieApi
    private var mListMovies = mutableListOf<MovieResults>()
    private var mObjectMovies : MoviesModel? = null

    private lateinit var sqliteHelper: SqliteHelper

    internal lateinit var sqliteController: SqliteController


    @NonNull
    var disposable: Disposable? = null

    override fun loadItems(context : Context, page : Int?) {
        mContext = context
        mView?.displayLoading(true)

        if (checkInternet()) {
            disposable = mRepository.instance
                .getMovies(Constants.API_KEY, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ listResponse ->
                    mView?.displayLoading(false)
                    val responseCode = listResponse.code()
                    when (responseCode) {
                        200 -> {
                            if(isInitialPage(page)) mListMovies.clear()
                            mObjectMovies = listResponse.body()
                            mListMovies.addAll(mObjectMovies!!.results)
                            mView?.displayItems(mListMovies)
                        }
                        401 -> {
                            mView?.displayError(listResponse.message())
                        }
                        404 -> {
                            mView?.displayError(listResponse.message())
                        }
                    }

                })
        } else{
            mView?.displayLoading(false)
            mView?.displayError("Verifique sua conexão")
        }
    }

    override fun loadFavoriteItems(context: Context) {
        sqliteController = SqliteController(context)
        if (sqliteController.favoriteMovies != null) {
            mView?.displayFavorites(sqliteController.favoriteMovies)
        }
    }

    override fun loadFavoriteDetail(context: Context, movieId : Int) {
        sqliteController = SqliteController(context)
        //mView?.displayFavoritesDetail(sqliteController.getMovieDetails(movieId))
    }


    fun isInitialPage(page: Int?) = page == INITIAL_PAGE

    private fun checkInternet(): Boolean {
        return NetworkConnection.isNetworkConnected(mContext)
    }

    override fun attachView(mvpView: MainMoviesContract.View) {
        mView = mvpView
    }

    override fun detachView() {
        mView = null
    }


}