package com.example.salon.ui.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.salon.data.model.app.ItemCategorySearch
import com.example.salon.databinding.ItemCategorySearchBinding


class AdapterCategorySearch(
    val context: Context,
    var itemCategorys: ArrayList<ItemCategorySearch>,
    var click: Click
) :
    RecyclerView.Adapter<AdapterCategorySearch.ViewHolderCategorySearch>() {
    class ViewHolderCategorySearch(public val binding: ItemCategorySearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemCategorySearch: ItemCategorySearch) {
            binding.category = itemCategorySearch
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCategorySearch {
        val binding = ItemCategorySearchBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolderCategorySearch(binding)
    }

    override fun getItemCount(): Int {
        return itemCategorys.size
    }

    override fun onBindViewHolder(holder: ViewHolderCategorySearch, position: Int) {
        holder.bind(itemCategorys[position])

        holder.itemView.setOnClickListener {
            if (holder.binding.cardCategory.tag == "1") {
                holder.binding.cardCategory.setCardBackgroundColor(Color.parseColor("#e8710b"))
                holder.binding.tvCategory.setTextColor(Color.WHITE)
                click.isClick(true,itemCategorys[position])
                holder.binding.cardCategory.tag = "2"
            } else {
                holder.binding.cardCategory.setCardBackgroundColor(Color.parseColor("#e0e7ed"))
                holder.binding.tvCategory.setTextColor(Color.BLACK)
                click.isClick(false,itemCategorys[position])
                holder.binding.cardCategory.tag = "1"
            }
        }
    }

}

interface Click {
    fun isClick(boolean: Boolean, itemCategorySearch: ItemCategorySearch)
}


