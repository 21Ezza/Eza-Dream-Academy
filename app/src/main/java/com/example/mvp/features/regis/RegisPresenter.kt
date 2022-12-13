package com.example.mvp.features.regis



class RegisPresenter {
    private var view: RegisView? = null

    fun onAttach(view: RegisView) {
        this.view = view
    }

    fun onDetach() {
        this.view = null
    }
}