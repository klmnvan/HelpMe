package com.example.matuleme.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.matuleme.R
import com.example.matuleme.databinding.ItemButtonCategoryBinding
import com.example.matuleme.objects.ProductsStorage

class AdapterCategory<T: Any>(private val listener: T): RecyclerView.Adapter<AdapterCategory.Holder>() {
    var listCategory: MutableList<String> = ArrayList()

    class Holder(item: View): RecyclerView.ViewHolder(item){
        val binding = ItemButtonCategoryBinding.bind(item)

        fun bind(el: String, listener: Listener) {
            with(binding){
                tTittle.text = el
                tTittle.setOnClickListener {
                    ProductsStorage.currenCategory = el
                    listener.updateCategory()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_button_category, parent, false)
        return Holder(view)
    }

    override fun getItemCount() = listCategory.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        if(ProductsStorage.currenCategory == ProductsStorage.allCategories[position]){
            holder.binding.mainContainer.setBackgroundResource(R.drawable.shape_blue_10rad)
            holder.binding.tTittle.setTextColor(Color.WHITE)
        }
        return holder.bind(listCategory[position], listener as Listener)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addElement(el: String) {
        listCategory.add(el)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        listCategory.clear()
        notifyDataSetChanged()
    }

    interface Listener {
        fun updateCategory()
    }
}