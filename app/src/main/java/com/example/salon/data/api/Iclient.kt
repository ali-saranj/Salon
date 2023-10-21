package com.example.salon.data.api

import retrofit2.Call
import retrofit2.http.GET

interface Iclient {

    @GET("home/")
    fun home():Call<com.example.salon.data.model.retrofit.home.Response>
}