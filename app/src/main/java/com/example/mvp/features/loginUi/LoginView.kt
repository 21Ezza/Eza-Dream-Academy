package com.example.mvp.features.loginUi

interface LoginView {
    fun onLoading()
    fun onFinishedLoading()
    fun onError(code: Int ,message: String)
    fun onSuccessLogin()
    fun onErrorUser()
    fun onErrorPassword()

}