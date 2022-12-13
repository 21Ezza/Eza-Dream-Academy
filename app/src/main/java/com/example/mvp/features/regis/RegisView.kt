package com.example.mvp.features.regis

interface RegisView {
    fun onLoading()
    fun onFinishedLoading()
    fun onError(message: String)
    fun onSuccessLogin()
}