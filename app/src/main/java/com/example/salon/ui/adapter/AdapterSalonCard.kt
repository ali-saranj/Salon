package com.example.salon.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.salon.data.api.Client
import com.example.salon.data.application.SalonApplication
import com.example.salon.data.model.app.SalonCard
import com.example.salon.databinding.ItemSalonBinding
import com.example.salon.ui.fragment.SalonFragment
import com.squareup.picasso.Picasso


class AdapterSalonCard(var fragmentManager: FragmentManager,var salonCards: ArrayList<SalonCard>) :
    RecyclerView.Adapter<AdapterSalonCard.ViewHolderSalonCard>() {

    class ViewHolderSalonCard(private val binding: ItemSalonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(salon: SalonCard) {
            binding.salon = salon
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderSalonCard {
        val binding = ItemSalonBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ViewHolderSalonCard(binding)
    }

    override fun getItemCount(): Int {
        return salonCards.size
    }

    override fun onBindViewHolder(holder: ViewHolderSalonCard, position: Int) {
        holder.bind(salonCards[position])
        holder.itemView.setOnClickListener {
            SalonFragment(salonCards[position].id.toString()).show(fragmentManager,"")
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