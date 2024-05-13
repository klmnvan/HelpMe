package com.example.matuleme.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.matuleme.R
import com.example.matuleme.databinding.ItemProductBaseBinding
import com.example.matuleme.models.ShopModelTest
import com.example.matuleme.objects.PrefManager
import com.example.matuleme.objects.ProductsStorage
import com.example.matuleme.objects.ProductsStorage.listProductBasket
import com.example.matuleme.objects.ProductsStorage.listProductFav

class AdapterProduct<T : Any>(private val listener: T): RecyclerView.Adapter<AdapterProduct.Holder>() {
    private var listProduct: MutableList<ShopModelTest> = ArrayList()

    class Holder(item: View): RecyclerView.ViewHolder(item) {
        val binding = ItemProductBaseBinding.bind(item)

        @SuppressLint("SetTextI18n")
        fun bind(el: ShopModelTest, list: Listener) {
            with(binding) {
                //Glide.with(root).load("http://i.imgur.com/uELerEW.png").into(img);
                tPrice.text = el.price.toString() + " р."
                tTittle.text = el.name
                btnAddFavorites.setOnClickListener {
                    if (listProductFav.contains(el)) {
                        listProductFav.removeAll{ it == el }
                    } else {
                        listProductFav.add(el)
                    }
                    list.updateListProduct()
                    PrefManager.listProductFav = listProductFav
                }
                btnAddShop.setOnClickListener {
                    if (listProductBasket.contains(el)) {
                        listProductBasket.removeAll{ it == el }
                    } else {
                        listProductBasket.add(el)
                    }
                    list.updateListProduct()
                    PrefManager.listProductFav = listProductFav
                }
                mainCont.setOnClickListener {
                    ProductsStorage.currentProduct = el
                    list.openCardProduct(el)
                    list.updateListProduct()
                    PrefManager.listProductFav = listProductFav
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product_base, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        with(holder){
            if (listProductFav.contains(listProduct[position])){
                binding.btnAddFavorites.setImageResource(R.drawable.icon_heart_red)
            }
            if(listProductBasket.contains(listProduct[position])){
                binding.btnAddShop.setImageResource(R.drawable.btn_delete_basket)
            }
        }
        holder.bind(listProduct[position], listener as Listener)
    }

    override fun getItemCount(): Int = listProduct.size

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

    interface Listener {

        fun openCardProduct(el: ShopModelTest)
        fun updateListProduct()

    }
}