package com.example.mvp.network.api

import com.example.mvp.network.Mapper.toUserPagination
import com.example.mvp.network.NetClient
import com.example.mvp.network.model.Movie
import com.example.mvp.network.model.UserPagination

class MovieApi {
    fun getMovie(page: Int = 1, onResponse: (Movie?, Throwable?) -> Unit) {
        NetClient
            .getApi("/posts$page", null) { responseBody, error ->
                if (error == null) {
                    val userPagination: UserPagination = responseBody?.toUserPagination() ?: UserPagination()
                    onResponse.invoke(Movie(), null)
                } else {
                    onResponse.invoke(null, error)
                }
            }
    }

}