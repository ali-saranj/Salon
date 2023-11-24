package com.example.salon.data.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Iclient {

    @GET("home/")
    fun home():Call<com.example.salon.data.model.retrofit.home.Response>

    @GET("allcategorysearch/")
    fun allCategorySearch():Call<List<com.example.salon.data.model.retrofit.allcategorysearch.ResponseItem>>

    @GET("search")
    fun search(@Query("s") s:String,@Query("category") category:String):Call<List<com.example.salon.data.model.retrofit.search.ResponseItem>>

    @GET("getsalon/")
    fun getSalon(@Query("id") id:String):Call<com.example.salon.data.model.retrofit.getsalon.Response>

    @POST("regester/")
    fun regester(@Body body: com.example.salon.data.model.retrofit.regester.Body):Call<com.example.salon.data.model.retrofit.regester.Response>


    @POST("login/")
    fun login(@Body body: com.example.salon.data.model.retrofit.login.Body) :Call<com.example.salon.data.model.retrofit.login.Response>
}