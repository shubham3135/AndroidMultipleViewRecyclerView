package com.shubhamkumarwinner.multipleviewrecyclerview.data.repository

import com.shubhamkumarwinner.multipleviewrecyclerview.data.network.Api
import com.shubhamkumarwinner.multipleviewrecyclerview.data.network.SafeApiCall
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: Api
) : SafeApiCall {
    suspend fun getMovies() = safeApiCall { api.getMovies() }
    suspend fun getDirectors() = safeApiCall { api.getDirectors() }
}