package com.example.prigui.androidtest.ui

/**
 * Created by prigui on 10/08/2018.
 */


class MainMoviesPresenter() : MainMoviesContract.Presenter {
    private var mView: MainMoviesContract.View? = null
    private var mRepository = MovieRepository

    override fun loadItems() {
//        mView?.displayLoading(true)
//        mRepository.getEventsDetail(eventId).singleSubscribe(
//                onSuccess = {
//                    mView?.displayLoading(false)
//                    mView?.displayItems(it)
//                },
//                onError = {
//                    mView?.displayLoading(false)
//                    mView?.displayError(it.message!!)
//                }
//        )
    }


    override fun attachView(mvpView: MainMoviesContract.View) {
        mView = mvpView
    }

    override fun detachView() {
        mView = null
    }


}