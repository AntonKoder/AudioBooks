package com.a4nt0n64r.audiobooks.di.dependencies

import com.a4nt0n64r.audiobooks.models.network.ApiResponse
import com.a4nt0n64r.audiobooks.repository.api.ApiService
import com.a4nt0n64r.audiobooks.repository.api.NetworkRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkRepoImpl @Inject constructor(private val apiService: ApiService) : NetworkRepository {

    override suspend fun getBooks(): ApiResponse {
        val call = apiService.getBooks()
        return call.execute().body()!!
    }
}
