package com.example.prigui.androidtest.ui.favorite

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.prigui.androidtest.R
import com.example.prigui.androidtest.remote.model.FavoriteMoviesModel
import com.example.prigui.androidtest.remote.model.MovieResults
import com.example.prigui.androidtest.ui.detail.createMovieDetailIntent
import com.example.prigui.androidtest.ui.detail.createMovieDetailIntentFavorite
import com.example.prigui.androidtest.ui.home.*
import kotlinx.android.synthetic.main.fragment_favorite.view.*

@SuppressLint("ValidFragment")
/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FavoriteFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FavoriteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoriteFragment(context : Context) : Fragment(), MainMoviesContract.View {


    private var mContext = context
    private lateinit var mView : View

    private val mPresenter: MainMoviesContract.Presenter by lazy {
        val presenter = MainMoviesPresenter()
        presenter.attachView(this)
        presenter
    }
    private var mAdapter: FavoriteAdapter? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mView = inflater!!.inflate(R.layout.fragment_favorite, container, false)
        setup()
        mPresenter.loadFavoriteItems(mContext)
        return mView
    }

    private fun setup() {
        setRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        mPresenter.loadFavoriteItems(mContext)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    private fun setRecyclerView() {
        val layoutManager = GridLayoutManager(mContext, 3)
        mView.favoriteRecyclerview!!.layoutManager = layoutManager

        mAdapter = FavoriteAdapter(mContext, object : FavoriteAdapter.OnItemClickListener {
            override fun onMovieClick(item: FavoriteMoviesModel) {
                startActivity(context.createMovieDetailIntentFavorite(item, false))
            }
        })

        mView.favoriteRecyclerview!!.adapter = mAdapter

        // Configurando um dividr entre linhas,.
        mView.favoriteRecyclerview!!.addItemDecoration(
                DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL))
        mView.favoriteSwiperefresh.setOnRefreshListener { mPresenter.loadFavoriteItems(mContext) }
    }

    override fun displayItems(movies: List<MovieResults>) {

    }


    override fun displayLoading(loading: Boolean) {
        mView.favoriteSwiperefresh.isRefreshing = loading
    }

    override fun displayError(message: String) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun displayFavorites(favorites: List<FavoriteMoviesModel>) {
        if (favorites.isEmpty()){
            Toast.makeText(mContext, "Não há favoritos", Toast.LENGTH_LONG).show()
        } else {
            mAdapter!!.mList = favorites
        }
    }

    override fun displayFavoritesDetail(favorites: FavoriteMoviesModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}