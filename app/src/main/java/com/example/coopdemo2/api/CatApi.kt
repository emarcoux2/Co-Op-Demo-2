package com.example.coopdemo2.api

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object CatApi {

    // ignoreUnknownKeys tells Kotlin's JSON parser to ignore fields
    // sent by the API that aren't defined in our data classes
    private val json = Json { ignoreUnknownKeys = true }

    // OkHttpClient is the underlying HTTP engine used by Retrofit
    private val client = OkHttpClient.Builder()
        // HttpLoggingInterceptor logs API calls in Logcat
        .addInterceptor(HttpLoggingInterceptor().apply {

            // logs the API response (e.g. 200, 404, 504)
            level = HttpLoggingInterceptor.Level.BASIC
        })
        // build the OkHTTPClient instance
        .build()

    // defines the root of all requests
    private val retrofit = Retrofit.Builder()
        // all endpoints append to this url
        .baseUrl("https://api.thecatapi.com/v1/")

        // this tells Retrofit to convert JSON responses into Kotlin objects
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))

        // attach the HTTP client
        .client(client)

        // build the Retrofit instance
        .build()

    // this is the actual implementation of CatApiService interface
    val service: CatApiService by lazy {
        retrofit.create(CatApiService::class.java)
    }
}