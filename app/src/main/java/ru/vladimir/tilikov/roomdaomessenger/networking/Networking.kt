package ru.vladimir.tilikov.roomdaomessenger.networking

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create

object Networking {

    private val okHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .followRedirects(true)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://www.google.com")
        .build()

    val fileApi: FileApi
        get() = retrofit.create()
}