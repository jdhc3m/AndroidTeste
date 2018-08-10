package com.example.prigui.androidtest.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.prigui.androidtest.R
import com.example.prigui.androidtest.remote.model.MoviesModel
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.ArrayList

class MainMoviesActivity : AppCompatActivity() {
    private var mPresenter: SkyContract.Presenter? = null
    private var mAdapter: MainMoviesAdapter? = null

    private var charactersDataSource: CharactersDataSource? = null
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        mPresenter = MainMoviesContract.Presenter(
                charactersDataSource,
                Schedulers.io(),
                AndroidSchedulers.mainThread(),
                this
        )
        setup()


    }

    private fun setup() {
        setPresenter()
    }

    private fun setPresenter() {
        charactersDataSource = CharactersRemoteDataSource()
        mPresenter = SkyPresenter(
                charactersDataSource,
                Schedulers.io(),
                AndroidSchedulers.mainThread(),
                this
        )
        mPresenter!!.subscribe()
        mPresenter!!.loadData()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter!!.unsubscribe()
    }

    private fun setRecyclerView() {
        val layoutManager = GridLayoutManager(this, 3)
        mRecyclerView!!.setLayoutManager(layoutManager)

        mAdapter = MainMoviesAdapter(this, ArrayList<MoviesModel>(0))
        mRecyclerView!!.setAdapter(mAdapter)

        // Configurando um dividr entre linhas,.
        mRecyclerView!!.addItemDecoration(
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    fun onFetchDataStarted() {

    }

    fun onFetchDataCompleted() {

    }

    fun onFetchDataSuccess(skyResponseModel: List<MoviesModel>) {
        val layoutManager = GridLayoutManager(this, 2)
        mRecyclerView!!.setup(layoutManager)

        mAdapter = MainMoviesAdapter(this, skyResponseModel)
        mRecyclerView!!.setAdapter(mAdapter)

        // Configurando um dividr entre linhas,.
        mRecyclerView!!.addItemDecoration(
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        //mAdapter = new MainMoviesAdapter(skyResponseModel);
    }

    fun onFetchDataError(e: Throwable) {

    }
}

