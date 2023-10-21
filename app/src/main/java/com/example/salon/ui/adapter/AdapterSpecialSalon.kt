package com.example.salon.ui.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.salon.R
import com.example.salon.data.api.Client
import com.example.salon.data.viewmodel.SpecialSalon
import com.example.salon.databinding.ItemSpecialSalonBinding
import com.squareup.picasso.Picasso


class AdapterSpecialSalon(var specialSalons: ArrayList<SpecialSalon>) :
    RecyclerView.Adapter<AdapterSpecialSalon.ViewHolderSpecialSalon>() {
    private lateinit var binding: ItemSpecialSalonBinding

    class ViewHolderSpecialSalon(
        private val binding: ItemSpecialSalonBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(salon: SpecialSalon) {
            binding.salon = salon
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderSpecialSalon {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_special_salon,
            parent,
            false
        )
        return ViewHolderSpecialSalon(binding)
    }

    override fun getItemCount(): Int {
        return specialSalons.size
    }

    override fun onBindViewHolder(holder: ViewHolderSpecialSalon, position: Int) {
        holder.bind(specialSalons[position])
    }

    companion object {
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun loadImage(view: ImageView, url: String) {
            Picasso.get().load(Client.BASE_URL + url).into(view)
        }
    }

}