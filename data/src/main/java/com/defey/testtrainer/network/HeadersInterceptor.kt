package com.defey.testtrainer.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeadersInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val requestWithHeaders = originalRequest.newBuilder()
            .header("App-Token", "secret")
            .build()

        return chain.proceed(requestWithHeaders)
    }
}