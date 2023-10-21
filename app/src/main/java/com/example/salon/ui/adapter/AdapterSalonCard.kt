package com.example.salon.ui.adapter

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.salon.R
import com.example.salon.data.api.Client
import com.example.salon.data.viewmodel.SalonCard
import com.example.salon.data.viewmodel.SpecialSalon
import com.example.salon.databinding.ItemSalonBinding
import com.example.salon.databinding.ItemSpecialSalonBinding
import com.squareup.picasso.Picasso


class AdapterSalonCard(var salonCards: ArrayList<SalonCard>) :
    RecyclerView.Adapter<AdapterSalonCard.ViewHolderSalonCard>() {
    private lateinit var binding: ItemSalonBinding

    class ViewHolderSalonCard(private val binding: ItemSalonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(salon: SalonCard) {
            binding.salon = salon
            binding.tvTitle.isSelected = true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderSalonCard {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_salon,
            parent,
            false
        )
        return ViewHolderSalonCard(binding)
    }

    override fun getItemCount(): Int {
        return salonCards.size
    }

    override fun onBindViewHolder(holder: ViewHolderSalonCard, position: Int) {
        holder.bind(salonCards[position])
    }

    companion object {
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun loadImage(view: ImageView, url: String) {
            Picasso.get().load(Client.BASE_URL + url).into(view)
        }
    }

}