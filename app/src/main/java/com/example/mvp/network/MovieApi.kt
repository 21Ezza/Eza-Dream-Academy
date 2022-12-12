package com.example.mvp.network

import com.example.mvp.network.model.Movie
import com.example.mvp.network.model.deserializeJson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import java.io.IOException

class MovieApi {
    fun getProducts(onResponse: (List<Movie>?) -> Unit) {
        val request = Request.Builder()
            .url("${HttpClient.baseUrl}${HttpClient.productEndpoint}")
            .build()

        HttpClient.client
            .newCall(request)
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val result = mutableListOf<Movie>()
                        val jsonArray = JSONArray(response.body?.string())
                        for (i in 0 until jsonArray.length()) {
                            val jsonObject = deserializeJson<Movie>(jsonArray.getString(i))
                            jsonObject?.let { result.add(it) }
                        }

                        onResponse.invoke(result)
                    }
                    response.body?.close()
                }
            })
    }
}