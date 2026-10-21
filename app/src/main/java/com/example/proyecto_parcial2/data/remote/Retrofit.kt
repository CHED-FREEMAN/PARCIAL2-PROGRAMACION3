package com.example.proyecto_parcial2.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    private const val BASE_URL_PRODUCT = "https://dummyjson.com/"
    private const val BASE_URL_FRANKFURTER = "https://api.frankfurter.dev/"
    private const val BASE_URL_PLACEHOLDER = "https://jsonplaceholder.typicode.com/"

    private val clienteHttp: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC })
        .build()

    val apiProduct: ProductApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_PRODUCT)
            .client(clienteHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductApiService::class.java)
    }

    val apiCambios: CambiosApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_FRANKFURTER)
            .client(clienteHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CambiosApiService::class.java)
    }

    val apiPlaceholder: PlaceholderApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_PLACEHOLDER)
            .client(clienteHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PlaceholderApiService::class.java)
    }
}
