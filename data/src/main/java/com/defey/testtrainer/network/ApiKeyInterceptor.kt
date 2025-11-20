package com.defey.testtrainer.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiKeyInterceptor @Inject constructor() : Interceptor {

    companion object {
        private const val API_KEY =
            "Bearer pdhO16atBIXogpPzaLDjDcl5Gpmbz9Mdl1mjhrhWZBuOgNCgxDlk7mMIbFcEc7mj"
        private const val API_KEY_HEADER = "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val requestWithApiKey = originalRequest.newBuilder()
            .header(API_KEY_HEADER, API_KEY)
            .build()

        return chain.proceed(requestWithApiKey)
    }
}