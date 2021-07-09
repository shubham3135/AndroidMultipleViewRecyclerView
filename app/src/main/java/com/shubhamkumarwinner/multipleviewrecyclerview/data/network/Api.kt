package com.shubhamkumarwinner.multipleviewrecyclerview.data.network

import com.shubhamkumarwinner.multipleviewrecyclerview.ui.HomeRecyclerViewItem
import retrofit2.http.GET

interface Api {

    @GET("movies")
    suspend fun getMovies(): List<HomeRecyclerViewItem.Movie>

    @GET("directors")
    suspend fun getDirectors(): List<HomeRecyclerViewItem.Director>
}