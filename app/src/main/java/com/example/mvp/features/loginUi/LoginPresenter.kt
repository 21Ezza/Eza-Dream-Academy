package com.example.mvp.features.loginUi

class LoginPresenter {
    companion object {

    }




    private var view: LoginView? = null

    fun onAttach(view: LoginView) {
        this.view = view
    }

    fun onDetach() {
        this.view = null
    }



    fun validateCredential(userName: String, password: String) {

        val isPasswordValid = password.contains("[a-z]".toRegex())
                && password.contains("[A-Z]".toRegex())
                && password.contains("[0-9]".toRegex())
                && password.length >= 8

        val isUsernameValid = userName.length > 5

        view?.onLoading()
        if (isPasswordValid && isUsernameValid) {
            view?.onSuccessLogin()
        } else if (!isUsernameValid && !isPasswordValid) {
            view?.onError("invalid username & password")
        } else if(!isPasswordValid){
            view?.onError("invalid password")
        } else{
            /*view?.onError("invalid username")*/
        }

        view?.onFinishedLoading()

    }

}