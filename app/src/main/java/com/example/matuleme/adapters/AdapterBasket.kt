package com.example.matuleme.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.matuleme.R
import com.example.matuleme.databinding.ItemProductInBasketBinding
import com.example.matuleme.models.ShopModelTest
import com.example.matuleme.objects.ProductsStorage

class AdapterBasket<T : Any>(private val listener: T): RecyclerView.Adapter<AdapterBasket.Holder>() {
    var listProduct: MutableList<ShopModelTest> = ArrayList()

    class Holder(item: View): RecyclerView.ViewHolder(item) {
        val binding = ItemProductInBasketBinding.bind(item)

        @SuppressLint("SetTextI18n")
        fun bind(el: ShopModelTest, list: Listener, count: Int) {
            with(binding) {
                //Glide.with(root).load("http://i.imgur.com/uELerEW.png").into(img);
                txtPrice.text = el.price.toString() + " р."
                txtTittle.text = el.name
                txtCountProduct.text = count.toString()
                btnPlus.setOnClickListener {
                    ProductsStorage.listProductBasket.add(el)
                    list.updateListProduct()
                }
                btnMinus.setOnClickListener {
                    ProductsStorage.listProductBasket.remove(el)
                    list.updateListProduct()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product_in_basket, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(listProduct[position], listener as Listener, ProductsStorage.listProductBasket.count { it == listProduct[position] })
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

        fun updateListProduct()

    }
}