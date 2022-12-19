package com.example.mvp.features.loginUi

import com.example.mvp.data.model.UserPagination

interface LoginView {
    fun onLoading()
    fun onFinishedLoading()
    fun onError(code: Int ,message: String)
    fun onErrorUser()
    fun onErrorPassword()
    fun onSuccessLogin(username: String, password: String)
    fun onSuccessGetUser(user: UserPagination)
    fun onSuccessRegister()

}