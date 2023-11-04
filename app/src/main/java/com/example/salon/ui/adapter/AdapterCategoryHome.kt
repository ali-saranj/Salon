package com.example.salon.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.salon.data.api.Client
import com.example.salon.data.viewmodel.ItemCategory
import com.example.salon.data.viewmodel.SalonCard
import com.example.salon.databinding.ItemCategoryHomeBinding
import com.squareup.picasso.Picasso


class AdapterCategoryHome(var fragmentManager: FragmentManager, var context: Context,var itemCategorys : ArrayList<ItemCategory>) :
    RecyclerView.Adapter<AdapterCategoryHome.ViewHolderCategory>() {
    private lateinit var binding: ItemCategoryHomeBinding

    class ViewHolderCategory(var fragmentManager: FragmentManager,var context: Context,public val binding: ItemCategoryHomeBinding) :
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
                binding.rvCategorySalon.adapter = AdapterSalonCard(fragmentManager,salonCards)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCategory {
        binding = ItemCategoryHomeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolderCategory(fragmentManager,context,binding)
    }

    override fun getItemCount(): Int {
        return itemCategorys.size
    }

    override fun onBindViewHolder(holder: ViewHolderCategory, position: Int) {
        holder.bind(itemCategorys[position])
    }

    companion object {
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun loadImage(view: ImageView, url: String) {
            Picasso.get().load(Client.BASE_URL + url).into(view)
        }
    }

}