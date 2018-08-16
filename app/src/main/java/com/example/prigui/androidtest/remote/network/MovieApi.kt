package com.example.prigui.androidtest.remote.network

import com.example.prigui.androidtest.remote.model.MoviesModel
import com.example.prigui.androidtest.utils.Constants
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by prigui on 13/08/2018.
 */
class MovieApi {

    private val myAppService: MoviesDataSource

    init {
        val gson = GsonBuilder()
                .setLenient()
                .create()

        val clientBuilder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        clientBuilder.addInterceptor(loggingInterceptor)

        val retrofitAuthentication = Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        val retrofit = Retrofit.Builder().baseUrl(Constants.MOVIE_API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        myAppService = retrofit.create(MoviesDataSource::class.java)
    }

    companion object {
        private val BASE_URL = Constants.MOVIE_API_URL_AUTHENTICATION
        private var apiClient: MovieApi? = null
        /**
         * Gets my app client.
         *
         * @return the my app client
         */
        val instance: MovieApi get() {
            if (apiClient == null) {
                apiClient = MovieApi()
            }
            return apiClient as MovieApi
        }
    }

    /**
     * Gets list of Movies.
     *
     * @return the list of Movies
     */

    //private val repository = MoviesRemoteDataSource

    fun getMovies(apiKey : String?, page : Int?): Observable<Response<MoviesModel>> {
        return myAppService.getMovies(apiKey, page)
    }

}