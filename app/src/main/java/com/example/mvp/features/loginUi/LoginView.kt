package com.example.mvp.features.loginUi

interface LoginView {
    fun onLoading()
    fun onFinishedLoading()
    fun onError(message: String)
    fun onSuccessLogin()

}