package com.defey.testtrainer.network

import com.defey.testtrainer.utils.NetworkResult
import retrofit2.Response

class ApiResponseHandler {

    suspend fun <T> handleApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        return try {
            val response = apiCall()
            if (response.isSuccessful) {
                response.body()?.let {
                    NetworkResult.Success(it)
                } ?: NetworkResult.Error("Empty response body")
            } else {
                NetworkResult.Error(
                    message = response.message(),
                    code = response.code()
                )
            }
        } catch (e: Exception) {
            NetworkResult.Error(
                message = e.message ?: "Unknown error occurred"
            )
        }
    }
}