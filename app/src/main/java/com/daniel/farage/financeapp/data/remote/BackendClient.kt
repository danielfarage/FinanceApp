package com.daniel.farage.financeapp.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BackendClient {
    val client: Retrofit by lazy {
        Retrofit.Builder().apply {
            baseUrl("https://careers.picpay.com/tests/mobdev/")
            client(OkHttpClient.Builder().build())
            addConverterFactory(GsonConverterFactory.create())
        }.build()
    }
}