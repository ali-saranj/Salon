package com.example.salon.data.repository

import com.example.salon.data.api.Iclient
import com.example.salon.data.model.retrofit.search.ResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class SearchRepository @Inject constructor(val api: Iclient) {
    suspend fun search(s: String, category: String, searchApi: SearchApi) {
        searchApi.loading(true)
        api.search(s, category).enqueue(object : Callback<List<ResponseItem>> {
            override fun onResponse(
                call: Call<List<ResponseItem>>,
                response: retrofit2.Response<List<ResponseItem>>
            ) {
                searchApi.loading(false)
                searchApi.successful(response)
            }

            override fun onFailure(call: Call<List<ResponseItem>>, t: Throwable) {
                searchApi.loading(false)
                searchApi.failure(t)
            }
        })
    }

    interface SearchApi {
        fun loading(isLoading: Boolean)

        fun successful(response: Response<List<ResponseItem>>)

        fun failure(t: Throwable)
    }

    suspend fun getCategory(getCategoryApi: GetCategoryApi) {
        getCategoryApi.loading(true)
        api.allCategorySearch().enqueue(object :
            Callback<List<com.example.salon.data.model.retrofit.allcategorysearch.ResponseItem>>{
            override fun onResponse(
                call: Call<List<com.example.salon.data.model.retrofit.allcategorysearch.ResponseItem>>,
                response: Response<List<com.example.salon.data.model.retrofit.allcategorysearch.ResponseItem>>
            ) {
                getCategoryApi.loading(false)
                getCategoryApi.successful(response)
            }

            override fun onFailure(
                call: Call<List<com.example.salon.data.model.retrofit.allcategorysearch.ResponseItem>>,
                t: Throwable
            ) {
                getCategoryApi.failure(t)
                getCategoryApi.loading(false)
            }
        })
    }

    interface GetCategoryApi {
        fun loading(isLoading: Boolean)

        fun successful(response: Response<List<com.example.salon.data.model.retrofit.allcategorysearch.ResponseItem>>)

        fun failure(t: Throwable)
    }
}


