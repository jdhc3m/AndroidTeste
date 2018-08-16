package com.example.prigui.androidtest.ui.detail

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.example.prigui.androidtest.R
import com.example.prigui.androidtest.remote.model.FavoriteMoviesModel
import com.example.prigui.androidtest.remote.model.MovieResults
import com.example.prigui.androidtest.ui.home.DetailMovieContract
import com.example.prigui.androidtest.ui.home.DetailMoviesPresenter
import com.example.prigui.androidtest.utils.Constants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*
import org.jetbrains.anko.intentFor


class MovieDetailActivity : AppCompatActivity(), DetailMovieContract.View {


    private lateinit var mMovie : MovieResults
    private var mIsFromHome : Boolean = true
    private lateinit var mMovieDetail : FavoriteMoviesModel

    private var mIsFavorite : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        setup()
    }

    private val mPresenter: DetailMovieContract.Presenter by lazy {
        val presenter = DetailMoviesPresenter()
        presenter.attachView(this)
        presenter
    }

    fun setup(){
        getExtra()
        checkIsFavorite()
        setupLayout()
    }

    private fun checkIsFavorite() {
        mPresenter.checkIsFavorite(this, mMovieDetail.movieId)
    }

    private fun setupLayout() {
        movieDetailNameTv.text = mMovieDetail.name
        movieDetailYearTv.text = mMovieDetail.date!!.substring(0,4)
        movieDetailOverviewTv.text = mMovieDetail.overview
        movieDetailImageIv

        if (!TextUtils.isEmpty(mMovieDetail.poster)) {
            Picasso.with(this).load(Constants.MOVIE_IMAGE_URL + mMovieDetail.poster)
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .into(movieDetailImageIv)
        }

        favoriteContainerLl.setOnClickListener{
            if (mIsFavorite){
                mPresenter.removeFavoriteDetail(this, mMovieDetail.movieId)
            } else {
                mPresenter.addFavoriteItems(this, mMovieDetail)
            }
        }

        movieDetailBuyTv.setOnClickListener {

        }

    }
    override fun updateFavorite(isFavorite: Boolean) {
        mIsFavorite = isFavorite
        if (isFavorite) {
            movieDetailFavoriteStarIv.setImageResource(R.drawable.ic_favorite_red_star)
            movieDetailFavoriteTv.text = "Remover dos Favoritos"
        } else {
            movieDetailFavoriteStarIv.setImageResource(R.drawable.ic_favorite_gray_star)
            movieDetailFavoriteTv.text = "Add aos Favoritos"
        }
    }


    private fun getExtra() {
        mIsFromHome = intent.getBooleanExtra(Constants.EXTRA_IS_FROM_HOME, true)
        if (mIsFromHome) {
            mMovie = intent.getSerializableExtra(Constants.EXTRA_MOVIE) as MovieResults
            mMovieDetail = FavoriteMoviesModel(mMovie.id, mMovie.title, mMovie.release_date, mMovie.poster_path, mMovie.overview)
        } else {
            mMovieDetail = intent.getSerializableExtra(Constants.EXTRA_FAVORITE_MOVIE) as FavoriteMoviesModel
        }
    }
}


fun Context.createMovieDetailIntent(movie : MovieResults, isFromHome : Boolean): Intent {
    val intent =  intentFor<MovieDetailActivity>(Constants.EXTRA_MOVIE to movie, Constants.EXTRA_IS_FROM_HOME to isFromHome)
    return intent
}

fun Context.createMovieDetailIntentFavorite(movieFavoriteMoviesModel: FavoriteMoviesModel, isFromHome : Boolean): Intent {
    val intent =  intentFor<MovieDetailActivity>(Constants.EXTRA_FAVORITE_MOVIE to movieFavoriteMoviesModel, Constants.EXTRA_IS_FROM_HOME to isFromHome)
    return intent
}
