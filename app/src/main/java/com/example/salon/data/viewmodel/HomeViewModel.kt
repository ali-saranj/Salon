package com.example.salon.data.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.salon.data.model.app.ItemCategory
import com.example.salon.data.model.app.SpecialSalon
import com.example.salon.data.model.retrofit.home.CategorysItem
import com.example.salon.data.model.retrofit.home.Response
import com.example.salon.data.model.retrofit.home.SpecialOffersSalonItem
import com.example.salon.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val homeRepasitory: HomeRepository):ViewModel() {
    private val _specialSalons = MutableLiveData<ArrayList<SpecialSalon>>()
    val specialSalons:LiveData<ArrayList<SpecialSalon>>
        get() = _specialSalons

    private val _category = MutableLiveData<ArrayList<ItemCategory>>()
    val category :LiveData<ArrayList<ItemCategory>>
        get() = _category

    private val _loading = MutableLiveData<Boolean>()
    val loading :LiveData<Boolean>
        get() = _loading

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            homeRepasitory.getHome(object : HomeRepository.HomeApi{
                override fun loading(isLoading: Boolean) {
                    _loading.postValue(isLoading)
                }

                override fun successful(response: retrofit2.Response<Response>) {
                    setDataspecialSalon(response.body()!!.specialOffersSalon)
                    setDataCategory(response.body()!!.categorys)
                }

                override fun failure(t: Throwable) {
                    Log.e("Error",t.message.toString())
                }
            })
        }
    }

    private fun setDataCategory(categorys: List<CategorysItem>) {
        if (categorys.isNotEmpty()) {
            val categorylist = ArrayList<ItemCategory>()
            categorys.forEach {
                categorylist.add(ItemCategory(it))
            }
            _category.value = categorylist
        }

    }

    private fun setDataspecialSalon(specialOffersSalon: List<SpecialOffersSalonItem>) {
        if (specialOffersSalon.isNotEmpty()) {
            var specialSalonList = ArrayList<SpecialSalon>()
            specialOffersSalon.forEach { specialSalonList.add(SpecialSalon(it))
            }
            _specialSalons.value = specialSalonList
        }
    }

}