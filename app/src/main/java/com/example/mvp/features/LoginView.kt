package com.example.mvp.features

interface LoginView {
    fun onLoading()
    fun onFinishedLoading()
    fun onError(message: String)
    fun onSuccessLogin()

}