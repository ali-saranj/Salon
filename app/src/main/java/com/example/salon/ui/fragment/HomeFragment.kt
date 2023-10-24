package com.example.salon.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.salon.data.api.Client
import com.example.salon.data.api.Iclient
import com.example.salon.data.model.retrofit.home.CategorysItem
import com.example.salon.data.model.retrofit.home.Response
import com.example.salon.data.model.retrofit.home.SpecialOffersSalonItem
import com.example.salon.data.viewmodel.ItemCategory
import com.example.salon.data.viewmodel.SpecialSalon
import com.example.salon.databinding.FragmentHomeBinding
import com.example.salon.ui.adapter.AdapterCategoryHome
import com.example.salon.ui.adapter.AdapterSpecialSalon
import retrofit2.Call
import retrofit2.Callback

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        getData()

        return binding.root
    }

    private fun ChangeViewPager() {
        Thread(Runnable {
                while (true) {
                    Thread.sleep(4000)
                    if (binding.viewPagerSpecialSalon.currentItem == binding.viewPagerSpecialSalon.childCount) {
                        binding.viewPagerSpecialSalon.currentItem = 0
                    }else{
                        binding.viewPagerSpecialSalon.currentItem = binding.viewPagerSpecialSalon.currentItem + 1
                    }
                }
        }).start()
    }

    private fun getData() {
        var iclient = Client.getClient().create(Iclient::class.java)
        iclient.home().enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if (response.isSuccessful && response.body() != null) {
                    setData(response.body()!!)
                    ChangeViewPager()
                } else {
                    Toast.makeText(context, "خطا", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setData(body: Response) {
        setRvSpecialSalon(body.specialOffersSalon)
        setRvCategory(body.categorys)
    }

    @SuppressLint("UseRequireInsteadOfGet")
    private fun setRvCategory(categorys: List<CategorysItem>) {
        Log.e("setRvCategory: ", categorys.toString())
        if (categorys.isNotEmpty()) {
            var categorylist = ArrayList<ItemCategory>()
            categorys.forEach {
                categorylist.add(ItemCategory(it))
            }
            binding.rvCategory.layoutManager = LinearLayoutManager(context)
            binding.rvCategory.adapter = AdapterCategoryHome(context!!, categorylist)
        }
    }

    private fun setRvSpecialSalon(specialOffersSalon: List<SpecialOffersSalonItem>) {
        if (specialOffersSalon.isNotEmpty()) {
            var specialSalonList = ArrayList<SpecialSalon>()
            specialOffersSalon.forEach {
                specialSalonList.add(SpecialSalon(it))
            }
            binding.viewPagerSpecialSalon.adapter = AdapterSpecialSalon(specialSalonList)
        } else {
            binding.layoutSpecialSalon.visibility = View.GONE
        }
    }

}
