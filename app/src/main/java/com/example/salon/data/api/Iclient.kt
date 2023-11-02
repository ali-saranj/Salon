package com.example.salon.data.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Iclient {

    @GET("home/")
    fun home():Call<com.example.salon.data.model.retrofit.home.Response>

    @GET("allcategorysearch/")
    fun allCategorySearch():Call<List<com.example.salon.data.model.retrofit.allcategorysearch.ResponseItem>>

    @GET("search")
    fun search(@Query("s") s:String,@Query("category") category:String):Call<List<com.example.salon.data.model.retrofit.search.ResponseItem>>
}