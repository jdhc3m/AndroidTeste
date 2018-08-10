package com.example.prigui.androidtest.ui

import com.example.prigui.androidtest.remote.model.MoviesModel

/**
 * Created by prigui on 10/08/2018.
 */


interface MainMoviesContract {

    interface View  {
        fun displayItems(events : MoviesModel)
        fun displayLoading(loading : Boolean)
        fun displayError(message: String)
        fun openInterestedActivity(eventId: String)
    }

    interface Presenter {
        fun loadItems()
    }
}