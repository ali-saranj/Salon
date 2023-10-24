package com.example.salon.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Client {
    companion object{
        val BASE_URL = "https://coodplusteam.pythonanywhere.com/"

        private var retrofit: Retrofit? = null;

        fun getClient():Retrofit{
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
    }
}