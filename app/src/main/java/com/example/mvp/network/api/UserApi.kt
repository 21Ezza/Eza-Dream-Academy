package com.example.mvp.network.api

import com.example.mvp.data.model.User
import com.example.mvp.network.model.UserPagination
import com.example.mvp.network.Mapper.toUserPagination
import com.example.mvp.network.NetClient

class UserApi {
    fun getUsers(page: Int = 1, onResponse: (UserPagination?, Throwable?) -> Unit) {
        NetClient
            .getApi("/users?page$page", null) { responseBody, error ->
                if (error == null) {
                    val userPagination: UserPagination = responseBody?.toUserPagination() ?: UserPagination()
                    onResponse.invoke(userPagination, null)
                } else {
                    onResponse.invoke(null, error)
                }
            }
    }
}
