package com.example.mvp.data.network

import com.example.mvp.data.api.ResponseStatus
import okhttp3.Response
import org.json.JSONObject

fun mapFailedResponse(response: Response): ResponseStatus.Failed {
    val jsonBody = response.body?.string().let { if(!it.isNullOrEmpty()) JSONObject(it) else JSONObject() }
    return ResponseStatus.Failed(response.code, jsonBody.optString("message", ""))
}