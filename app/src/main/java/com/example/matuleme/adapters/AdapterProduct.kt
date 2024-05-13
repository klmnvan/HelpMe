package com.example.matuleme.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.matuleme.R
import com.example.matuleme.databinding.ItemProductBaseBinding
import com.example.matuleme.models.ShopModelTest
import com.example.matuleme.objects.UserData

class AdapterProduct: RecyclerView.Adapter<AdapterShop.Holder>(){

    private var listProduct: MutableList<ShopModelTest> = ArrayList()

    class Holder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = ItemProductBaseBinding.bind(item)

        @SuppressLint("SetTextI18n")
        fun bind(el: ShopModelTest) {
            with(binding) {
                Glide.with(root).load("https://i.imgur.com/uELerEW.png").into(img);
                tPrice.text = el.price.toString() + " р."
                tTittle.text = el.name
                btnAddShop.setOnClickListener {
                    UserData.addShop(el)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterShop.Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product_base, parent, false)
        return AdapterShop.Holder(view)
    }

    override fun getItemCount(): Int = listProduct.size

    override fun onBindViewHolder(holder: AdapterShop.Holder, position: Int) {
        holder.bind(listProduct[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addElement(el: ShopModelTest) {
        listProduct.add(el)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        listProduct.clear()
        notifyDataSetChanged()
    }

    fun

    interface Listener {
        fun addFavourite(chat: ShopModelTest)
    }
}