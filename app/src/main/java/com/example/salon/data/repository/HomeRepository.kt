package com.example.salon.data.repository

import com.example.salon.data.api.Iclient
import com.example.salon.data.model.retrofit.home.Response
import retrofit2.Call
import retrofit2.Callback
import javax.inject.Inject

class HomeRepository @Inject constructor(val api:Iclient) {

   suspend fun getHome(homeApi: HomeApi){
       homeApi.loading(true)
        api.home().enqueue(object: Callback<Response>{
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                homeApi.successful(response)
                homeApi.loading(false)
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                homeApi.failure(t)
                homeApi.loading(false)
            }
        })
    }

    interface HomeApi{
        fun loading(isLoading:Boolean)

        fun successful(response: retrofit2.Response<Response>)

        fun failure(t: Throwable)
    }

}