package com.example.proyecto_parcial2.data.remote

import kotlin.jvm.java

object Retrofit {
    private const val BASE_URL_DUMMY = "https://dummyjson.com/"
    private const val BASE_URL_FRANKFURTER = "https://api.frankfurter.dev/"

    private const val BASE_URL_PLACEHOLDER = "https://jsonplaceholder.typicode.com/"

    private val clienteHttp: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC })
        .build()

    val apiDummy: DummyApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_DUMMY)
            .client(clienteHttp)
            .addConverterFactory(GsonConverterFactory.create()) // JSON <-> objetos Kotlin
            .build()
            .create(DummyApiService::class.java)
    }
    val apiCambios: CambiosApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_FRANKFURTER)
            .client(clienteHttp)
            .addConverterFactory(GsonConverterFactory.create()) // JSON <-> objetos Kotlin
            .build()
            .create(CambiosApiService::class.java)
    }
    val apiPlaceholder: PlaceholderApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_PLACEHOLDER)
            .client(clienteHttp)
            .addConverterFactory(GsonConverterFactory.create()) // JSON <-> objetos Kotlin
            .build()
            .create(PlaceholderApiService::class.java)
    }


}