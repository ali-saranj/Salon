package com.example.salon.data.application

import com.example.salon.data.api.Client
import com.example.salon.data.api.Iclient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModele {

    @Provides
    @Singleton
    fun provideBaseUrl(): String = Client.BASE_URL

    @Provides
    @Singleton
    fun provideRetrofit(baseurl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideIclient(api:Retrofit): Iclient = api.create(Iclient::class.java)
}