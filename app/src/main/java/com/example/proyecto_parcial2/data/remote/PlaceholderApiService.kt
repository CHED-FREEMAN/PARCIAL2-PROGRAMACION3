package com.example.proyecto_parcial2.data.remote

interface PlaceholderApiService {
    @GET("posts")
    suspend fun getPosts(): Response<List<PostResponse>>
    }
}