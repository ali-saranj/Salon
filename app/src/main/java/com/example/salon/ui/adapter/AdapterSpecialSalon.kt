package com.example.salon.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.salon.R
import com.example.salon.data.api.Client
import com.example.salon.data.application.SalonApplication
import com.example.salon.data.model.app.SpecialSalon
import com.example.salon.databinding.ItemSpecialSalonBinding
import com.example.salon.ui.fragment.SalonFragment
import com.squareup.picasso.Picasso


class AdapterSpecialSalon(val fragmentManager: FragmentManager,var specialSalons: ArrayList<SpecialSalon>) :
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

        holder.itemView.setOnClickListener {
            SalonFragment(specialSalons[position].id.toString()).show(fragmentManager,"")
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