package com.example.mvp.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddUserModel(
    var name: String,
    var job: String
)
