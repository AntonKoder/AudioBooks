package com.a4nt0n64r.audiobooks.di.dependencies

import com.a4nt0n64r.audiobooks.models.network.ApiResponse
import com.a4nt0n64r.audiobooks.models.toBookUI
import com.a4nt0n64r.audiobooks.repository.api.ApiService
import com.a4nt0n64r.audiobooks.repository.api.MyCallBack
import com.a4nt0n64r.audiobooks.repository.api.NetworkRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkRepoImpl @Inject constructor(private val apiService: ApiService) : NetworkRepository {

    override fun getBooks(myCallBack: MyCallBack) {
        val call = apiService.getBooks()
        call.enqueue(object : Callback<ApiResponse> {

            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val asd: ApiResponse = response.body()!!
                    myCallBack.onSuccess(asd.items.map { it.toBookUI() })
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                myCallBack.onError("NETWORK ERROR!")
            }
        })
    }
}
