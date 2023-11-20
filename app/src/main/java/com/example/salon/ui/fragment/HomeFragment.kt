package com.example.salon.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.salon.data.api.Iclient
import com.example.salon.data.model.retrofit.home.CategorysItem
import com.example.salon.data.model.retrofit.home.Response
import com.example.salon.data.model.retrofit.home.SpecialOffersSalonItem
import com.example.salon.data.model.app.ItemCategory
import com.example.salon.data.model.app.SpecialSalon
import com.example.salon.data.viewmodel.HomeViewModel
import com.example.salon.databinding.FragmentHomeBinding
import com.example.salon.ui.adapter.AdapterCategoryHome
import com.example.salon.ui.adapter.AdapterSpecialSalon
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class HomeFragment : Fragment() {

    @Inject
    lateinit var client: Retrofit
    lateinit var binding: FragmentHomeBinding

    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        setData()
        return binding.root
    }




    private fun changeViewPager() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            while (true) {
                delay(5000)
                if (binding.viewPagerSpecialSalon.currentItem == binding.viewPagerSpecialSalon.childCount) {
                    binding.viewPagerSpecialSalon.currentItem = 0
                } else {
                    binding.viewPagerSpecialSalon.currentItem =
                        binding.viewPagerSpecialSalon.currentItem + 1
                }
            }

        }

    }

    private fun setData() {
        loading()
        setRvSpecialSalon()
        setRvCategory()
        changeViewPager()
    }

    private fun loading() {
        homeViewModel.loading.observeForever {
            if (it){
                binding.progressLoading.visibility = View.VISIBLE
                binding.layoutAllCantent.visibility = View.GONE
            }else{
                binding.progressLoading.visibility = View.GONE
                binding.layoutAllCantent.visibility = View.VISIBLE
            }
        }
    }


    @SuppressLint("UseRequireInsteadOfGet")
    private fun setRvCategory() {
       homeViewModel.category.observeForever {
           binding.rvCategory.adapter =
               AdapterCategoryHome(parentFragmentManager, context!!, it)
       }

    }

    private fun setRvSpecialSalon() {
        homeViewModel.specialSalons.observeForever {
            binding.viewPagerSpecialSalon.adapter = AdapterSpecialSalon(parentFragmentManager, it)
        }
    }


}
