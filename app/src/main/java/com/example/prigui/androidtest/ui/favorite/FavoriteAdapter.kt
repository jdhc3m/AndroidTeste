package com.example.prigui.androidtest.ui.favorite

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.prigui.androidtest.R
import com.example.prigui.androidtest.remote.model.FavoriteMoviesModel
import com.example.prigui.androidtest.utils.BaseRecyclerViewAdapter
import com.example.prigui.androidtest.utils.Constants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

/**
 * Created by prigui on 10/08/2018.
 */

class FavoriteAdapter(context : Context, private val onItemClickListener: OnItemClickListener) : BaseRecyclerViewAdapter(context) {
    override fun getDisplayableItemsCount() = mList.size

    override fun onBindRecyclerViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is ItemViewHolder) {
            holder.bindItems(mList[position])
        }
    }

    override fun getItemViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent!!.context).inflate(R.layout.item_movie, parent, false)
        return ItemViewHolder(v)
    }

    var mList: List<FavoriteMoviesModel> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    //the class is hodling the list view
    inner class ItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bindItems(favoriteMovie: FavoriteMoviesModel) {
            itemView.apply {
                if (!TextUtils.isEmpty(favoriteMovie.poster)) {
                    Picasso.with(itemView.context).load(Constants.MOVIE_IMAGE_URL + favoriteMovie.poster)
                            .error(R.drawable.placeholder)
                            .placeholder(R.drawable.placeholder)
                            .into(movieImageIv)
                }

                movieNameTv.text = favoriteMovie.name
                movieYearTv.text = favoriteMovie.date!!.substring(0,4)
            }

            itemView.setOnClickListener{
                onItemClickListener.onMovieClick(favoriteMovie)
            }

        }
    }

    interface OnItemClickListener {
        fun onMovieClick(item: FavoriteMoviesModel)
    }
}