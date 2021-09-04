package com.a4nt0n64r.audiobooks.repository.api

import com.a4nt0n64r.audiobooks.models.network.ApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("book")
    fun getBooks(): Call<ApiResponse>
}
