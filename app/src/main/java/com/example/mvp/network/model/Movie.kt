package com.example.mvp.network.model

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Movie (
    var userId : Int? = null,
    var id: Int? = null,
    var title: String = "",
    var body: String = ""
)

