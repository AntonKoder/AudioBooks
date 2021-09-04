package com.a4nt0n64r.audiobooks.repository.api

import com.a4nt0n64r.audiobooks.models.network.ApiResponse

interface NetworkRepository {
    suspend fun getBooks(): ApiResponse
}
