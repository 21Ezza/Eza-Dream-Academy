package com.example.mvp.network.model

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

object MoshiExtensions {
    val moshi: Moshi = Moshi.Builder().build()
}

inline fun<reified T> deserializeJson(string: String): T? {
    val adapter: JsonAdapter<T> = MoshiExtensions.moshi.adapter(T::class.java)
    return adapter.fromJson(string)
}

inline fun<reified T> T.serialized(): String {
    return MoshiExtensions.moshi.adapter(T::class.java).toJson(this)
}