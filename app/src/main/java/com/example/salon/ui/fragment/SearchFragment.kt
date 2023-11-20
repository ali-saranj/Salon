package com.example.salon.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.salon.data.api.Iclient
import com.example.salon.data.model.retrofit.allcategorysearch.ResponseItem
import com.example.salon.data.model.app.ItemCategorySearch
import com.example.salon.data.model.app.SalonCard
import com.example.salon.data.viewmodel.SearchViewModel
import com.example.salon.databinding.FragmentSearchBinding
import com.example.salon.ui.adapter.AdapterCategorySearch
import com.example.salon.ui.adapter.AdapterSalonCardGraid
import com.example.salon.ui.adapter.Click
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.util.Objects
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {
    lateinit var binding: FragmentSearchBinding
    val searchViewModel: SearchViewModel by viewModels()


    var categorys = ArrayList<String>()
    var search = ""

    @Inject
    lateinit var client: Retrofit
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        getData()

        return binding.root
    }

    private fun getData() {
        loading()
        setDataCategory()
        setDataSalon()
    }

    private fun loading() {
        loadingCategory()
        loadingSalon()
    }

    private fun loadingSalon() {
        searchViewModel.loadingSalonList.observeForever {
            if (it){
                binding.loadingSalon.visibility = View.VISIBLE
                binding.rvSalon.visibility = View.INVISIBLE
            }else{
                binding.rvSalon.visibility = View.VISIBLE
                binding.loadingSalon.visibility = View.GONE
            }
        }
    }

    private fun loadingCategory() {
        searchViewModel.loadingCategoryList.observeForever {
            if (it){
                binding.loadingCategory.visibility = View.VISIBLE
                binding.rvCategorySearch.visibility = View.INVISIBLE
            }else{
                binding.rvCategorySearch.visibility = View.VISIBLE
                binding.loadingCategory.visibility = View.GONE
            }
        }    }


    private fun setDataCategory() {
        searchViewModel.categoryList.observeForever {
            binding.rvCategorySearch.adapter =
                AdapterCategorySearch(
                    requireContext(),
                    it,
                    ClickOnItemCategory()
                )
        }
    }

    private fun ClickOnItemCategory(): Click {
        return object : Click {
            override fun isClick(boolean: Boolean, itemCategorySearch: ItemCategorySearch) {
                if (boolean) {
                    categorys.add(itemCategorySearch.title)
                    searchViewModel.category.value = categorys
                } else {
                    categorys.remove(itemCategorySearch.title)
                    searchViewModel.category.value = categorys
                }
                searchViewModel.getSalon()
            }
        }
    }

    private fun setDataSalon() {
        searchViewModel.salonList.observeForever {
            binding.rvSalon.adapter =
                AdapterSalonCardGraid(parentFragmentManager, it)
        }


    }
}