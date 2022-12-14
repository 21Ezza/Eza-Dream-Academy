package com.example.mvp.data.api

import com.example.mvp.data.model.RegisterResult
import com.example.mvp.data.network.NetworkClient
import com.example.mvp.data.network.deserializeJson
import com.example.mvp.data.network.mapFailedResponse
import com.example.mvp.data.network.serialized
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class CredentialApi {
    fun registerUser(email: String, password: String): Flow<ResponseStatus<RegisterResult>> = flow {
        val model = LoginRegisterModel(email, password)
        try {
            val result = NetworkClient
                .makeCallApi("/register?delay=2", NetworkClient.METHOD.POST, model.serialized())
                .execute()
            val response = if (result.isSuccessful) {
                val data: RegisterResult = deserializeJson<RegisterResult>(result.body?.string() ?: "") ?: RegisterResult()
                ResponseStatus.Success(data)
            } else {
                mapFailedResponse(result)
            }
            emit(response)
            result.body?.close()
        } catch (e: IOException) {
            emit(ResponseStatus.Failed(-1, e.message.toString(), e))
        }
    }
}