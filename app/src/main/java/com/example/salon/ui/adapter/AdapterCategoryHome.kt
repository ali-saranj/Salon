package com.example.salon.ui.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.salon.R
import com.example.salon.data.api.Client
import com.example.salon.data.viewmodel.ItemCategory
import com.example.salon.data.viewmodel.SalonCard
import com.example.salon.data.viewmodel.SpecialSalon
import com.example.salon.databinding.ItemCategoryHomeBinding
import com.example.salon.databinding.ItemSalonBinding
import com.example.salon.databinding.ItemSpecialSalonBinding
import com.squareup.picasso.Picasso


class AdapterCategoryHome( var context: Context,var itemCategorys : ArrayList<ItemCategory>) :
    RecyclerView.Adapter<AdapterCategoryHome.ViewHolderCategory>() {
    private lateinit var binding: ItemCategoryHomeBinding

    class ViewHolderCategory(var context: Context,public val binding: ItemCategoryHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemCategory: ItemCategory) {
            binding.tvTitleCategoryHome.text = itemCategory.title

            if (!itemCategory.salonItems.isNullOrEmpty()){
                var salonCards = ArrayList<SalonCard>()
                itemCategory.salonItems.forEach {
                    salonCards.add(SalonCard(it))
                }
                binding.rvCategorySalon.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true)
                binding.rvCategorySalon.isNestedScrollingEnabled = true
                binding.rvCategorySalon.adapter = AdapterSalonCard(salonCards)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCategory {
        binding = ItemCategoryHomeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolderCategory(context,binding)
    }

    override fun getItemCount(): Int {
        return itemCategorys.size
    }

    override fun onBindViewHolder(holder: ViewHolderCategory, position: Int) {
        binding.tvTitleCategoryHome.text = itemCategorys[position].title

        if (!itemCategorys[position].salonItems.isNullOrEmpty()){
            var salonCards = ArrayList<SalonCard>()
            itemCategorys[position].salonItems.forEach {
                salonCards.add(SalonCard(it))
            }
            binding.rvCategorySalon.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            binding.rvCategorySalon.adapter = AdapterSalonCard(salonCards)
        }else{
            Log.e("onBindViewHolder: ","a" )
        }
    }

    companion object {
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun loadImage(view: ImageView, url: String) {
            Picasso.get().load(Client.BASE_URL + url).into(view)
        }
    }

}