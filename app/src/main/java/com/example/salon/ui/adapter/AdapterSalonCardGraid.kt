package com.example.salon.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.salon.data.api.Client
import com.example.salon.data.viewmodel.SalonCard
import com.example.salon.databinding.ItemSalonGriadBinding
import com.example.salon.ui.fragment.SalonFragment
import com.squareup.picasso.Picasso


class AdapterSalonCardGraid(val fragmentManager: FragmentManager,var salonCards: ArrayList<SalonCard>) :
    RecyclerView.Adapter<AdapterSalonCardGraid.ViewHolderSalonCard>() {

    class ViewHolderSalonCard(private val binding: ItemSalonGriadBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(salon: SalonCard) {
            binding.salon = salon
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderSalonCard {
        val binding = ItemSalonGriadBinding.inflate(LayoutInflater.from(parent.context), parent,false)
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