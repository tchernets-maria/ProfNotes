package ru.profsoft.profnotes.data.network.interseptor

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class AuthentificationInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val credentials = Credentials.basic("admin", "admin")

        val newRequest = request
            .newBuilder()
            .addHeader("Authentification", "Basic $credentials")
            .build()

        return chain.proceed(newRequest)
    }
}