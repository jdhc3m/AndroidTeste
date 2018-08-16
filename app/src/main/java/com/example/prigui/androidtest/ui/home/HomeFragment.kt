package com.example.prigui.androidtest.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.prigui.androidtest.R
import com.example.prigui.androidtest.remote.model.FavoriteMoviesModel
import com.example.prigui.androidtest.remote.model.MovieResults
import com.example.prigui.androidtest.ui.detail.createMovieDetailIntent
import com.example.prigui.androidtest.utils.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.fragment_favorite.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*

@SuppressLint("ValidFragment")
/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [HomeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment(context : Context) : Fragment(), MainMoviesContract.View {


    private var mContext = context
    private lateinit var mView : View

    private lateinit var mEndlessScrollListener: EndlessRecyclerViewScrollListener

    private val mPresenter: MainMoviesContract.Presenter by lazy {
        val presenter = MainMoviesPresenter()
        presenter.attachView(this)
        presenter
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mView = inflater!!.inflate(R.layout.fragment_home, container, false)
        setup()
        mPresenter.loadItems(mContext, INITIAL_PAGE)
        return mView

    }

    private fun setup() {
        setSwipeRefresh()
    }

    private fun setSwipeRefresh() {
        mView.homeSwiperefresh.setOnRefreshListener {  mPresenter.loadItems(mContext, INITIAL_PAGE) }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }


    private val mAdapter: RecyclerView by lazy {
        val layoutManager = GridLayoutManager(mContext, 3)
        mView.homeRecyclerview!!.layoutManager = layoutManager
        val adapter = initializeBenefitsAdapter()
        mView.homeRecyclerview.adapter = adapter
        mEndlessScrollListener = object : EndlessRecyclerViewScrollListener(mView.homeRecyclerview!!.layoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                mPresenter.loadItems(mContext, page+1)
            }
        }
        mEndlessScrollListener.setAdapter(adapter)
        mView.homeRecyclerview!!.addOnScrollListener(mEndlessScrollListener)
        mView.homeRecyclerview!!
    }

    private fun initializeBenefitsAdapter() = MainMoviesAdapter(mContext, object : MainMoviesAdapter.OnItemClickListener {
        override fun onMovieClick(item: MovieResults) {
            startActivity(context.createMovieDetailIntent(item, true))
        }

    })

    override fun displayItems(movies: List<MovieResults>) {
        (mAdapter!!.adapter as MainMoviesAdapter).mList = movies
    }

    override fun displayLoading(loading: Boolean) {
        mView.homeSwiperefresh.isRefreshing = loading
    }

    override fun displayError(message: String) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
    }
    override fun displayFavorites(favorites: List<FavoriteMoviesModel>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displayFavoritesDetail(favorites: FavoriteMoviesModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}


