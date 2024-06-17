package com.lexleontiev.tapyou.test.app.remote

import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBuilder {

    fun getRetrofit() = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://hr-challenge.dev.tapyou.com")
        .client(createHttpClientInterceptor())
        .build()

    fun getApi(retrofit: Retrofit): Api = retrofit.create((Api::class.java))

    private fun createHttpClientInterceptor() = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .build()
}
