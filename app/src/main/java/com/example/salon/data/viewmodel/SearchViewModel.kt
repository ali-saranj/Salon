package com.example.salon.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.salon.data.model.app.ItemCategorySearch
import com.example.salon.data.model.app.SalonCard
import com.example.salon.data.model.retrofit.allcategorysearch.ResponseItem
import com.example.salon.data.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(val searchRepository: SearchRepository) : ViewModel() {
    private val _categoryList = MutableLiveData<ArrayList<ItemCategorySearch>>()
    val categoryList: LiveData<ArrayList<ItemCategorySearch>>
        get() = _categoryList

    private val _loadingCategoryList = MutableLiveData<Boolean>()
    val loadingCategoryList: LiveData<Boolean>
        get() = _loadingCategoryList


    private val _salonList = MutableLiveData<ArrayList<SalonCard>>()
    val salonList: LiveData<ArrayList<SalonCard>>
        get() = _salonList

    private val _loadingSalonList = MutableLiveData<Boolean>()
    val loadingSalonList: LiveData<Boolean>
        get() = _loadingSalonList


    val s = MutableLiveData<String>()
    val category = MutableLiveData<ArrayList<String>>()

    init {
        getCategory()
        getSalon()
    }

    fun getSalon() {
        viewModelScope.launch(Dispatchers.IO) {
            var s = s.value
            if (s == null)
                s = ""
            var categoryy = category.value
            if (categoryy==null)
                categoryy = ArrayList()

            searchRepository.search(
                s,
                java.lang.String.join(",", categoryy),
                object : SearchRepository.SearchApi {
                    override fun loading(isLoading: Boolean) {
                        _loadingSalonList.postValue(isLoading)
                    }

                    override fun successful(response: Response<List<com.example.salon.data.model.retrofit.search.ResponseItem>>) {
                        setDataSalon(response.body())
                    }

                    override fun failure(t: Throwable) {

                    }
                })
        }
    }

    private fun setDataSalon(body: List<com.example.salon.data.model.retrofit.search.ResponseItem>?) {
        if (!body.isNullOrEmpty()) {
            val salonCardList = ArrayList<SalonCard>()
            body.forEach {
                salonCardList.add(SalonCard(it))
            }
            _salonList.value = salonCardList
        }
    }

    private fun getCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            searchRepository.getCategory(object : SearchRepository.GetCategoryApi {
                override fun loading(isLoading: Boolean) {
                    _loadingCategoryList.postValue(isLoading)
                }

                override fun successful(response: Response<List<ResponseItem>>) {
                    setCategory(response.body())
                }

                override fun failure(t: Throwable) {

                }
            })
        }
    }

    private fun setCategory(body: List<ResponseItem>?) {
        if (!body.isNullOrEmpty()) {
            val itemCategorySearchList = ArrayList<ItemCategorySearch>()
            body.forEach {
                itemCategorySearchList.add(ItemCategorySearch(it))
            }
            _categoryList.value = itemCategorySearchList
        }

    }


}