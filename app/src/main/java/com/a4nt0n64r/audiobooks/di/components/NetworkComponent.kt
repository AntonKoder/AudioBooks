package com.a4nt0n64r.audiobooks.di.components

import com.a4nt0n64r.audiobooks.di.modules.NetworkModule
import com.a4nt0n64r.audiobooks.repository.api.ApiService
import com.a4nt0n64r.audiobooks.repository.api.NetworkRepository
import com.google.gson.Gson
import dagger.Component
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface NetworkComponent {

    fun provideInterceptor(): HttpLoggingInterceptor

    fun provideOkHttpClient(): OkHttpClient

    fun provideGson(): Gson

    fun provideRetrofit(): Retrofit

    fun provideApiService(): ApiService

    fun provideNetworkRepository(): NetworkRepository

//    fun inject(datamanager: DataManager)
}
