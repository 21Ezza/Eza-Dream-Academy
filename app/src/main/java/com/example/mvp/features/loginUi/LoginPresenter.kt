package com.example.mvp.features.loginUi

class LoginPresenter {
    companion object {

    }

    private var isUsernameValid = true
    private var isPasswordValid = true



    private var view: LoginView? = null

    fun onAttach(view: LoginView) {
        this.view = view
    }

    fun onDetach() {
        this.view = null
    }

    fun validateUser(userName: String): Boolean {
        val isUsernameValid = userName.length >= 5
        if (!isUsernameValid){
            view?.onError(1, "invalid username")
        } else {
            view?.onErrorUser()
        }
        return isUsernameValid
    }

    fun validatePassword(password: String): Boolean {
        val isPasswordValid = password.contains("[a-z]".toRegex())
                && password.contains("[A-Z]".toRegex())
                && password.contains("[0-9]".toRegex())
                && password.length >= 8
        if (!isPasswordValid){
            view?.onError(2,"invalid password")
        } else{
            view?.onErrorPassword()
        }
        return isPasswordValid
    }



    fun validateCredential() {

        if (isPasswordValid && isUsernameValid){
            view?.onSuccessLogin()
        }
        view?.onFinishedLoading()
    }

}