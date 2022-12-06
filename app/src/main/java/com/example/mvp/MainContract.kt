package com.example.mvp

import com.example.mvp.data.model.User

interface MainContract {
    interface Presenter {
        fun onAttach()
        fun onDetach()
    }

    interface View {
        fun onLoading()
        fun onFinishedLoading()
        fun onError(message: String)
        fun onSuccessGetUsers(users: List<User>) {}
        fun onSuccessAddUser() {}
    }
}