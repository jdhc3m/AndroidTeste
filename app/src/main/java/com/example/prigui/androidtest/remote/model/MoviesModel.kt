package com.example.prigui.androidtest.remote.model

import java.io.Serializable

/**
 * Created by prigui on 10/08/2018.
 */
data class MoviesModel (
        var page :Int?,
        var results : List<MovieResults>,
        var total_results : Int?,
        var total_pages : Int?

)

data class MovieResults(
        var poster_path : String?,
        var adult : Boolean?,
        var overview : String?,
        var release_date : String?,
        var genre_ids : ArrayList<Int>?,
        var id : Int,
        var original_title : String?,
        var original_language : String?,
        var title : String?,
        var backdrop_path : String?,
        var popularity : Double?,
        var vote_count : Int?,
        var video : Boolean?,
        var vote_average : Double?

) : Serializable

