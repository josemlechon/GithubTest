package com.jml.github.challenge.data.network

import com.jml.github.challenge.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HeadersInterceptor(
    private val token: String = BuildConfig.TOKEN_GITHUB_API,
    private val versionAPI: String = "2022-11-28"
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {

        val originalRequest = request()

        val newRequest = originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .addHeader("Accept", "application/vnd.github+json")
            .addHeader("X-GitHub-Api-Version", versionAPI)
            .build()

        chain.proceed(newRequest)
    }
}