package com.a4nt0n64r.audiobooks.di.components

import androidx.fragment.app.ListFragment
import com.a4nt0n64r.audiobooks.di.modules.NetworkModule
import com.a4nt0n64r.audiobooks.di.dependencies.DataManager
import com.a4nt0n64r.audiobooks.repository.api.ApiService
import com.a4nt0n64r.audiobooks.repository.api.NetworkRepository
import com.a4nt0n64r.audiobooks.repository.realm.DatabaseImpl
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

    fun provideDataManager(): DataManager

    fun provideDatabase(): DatabaseImpl

    fun inject(fragment: ListFragment)
}
